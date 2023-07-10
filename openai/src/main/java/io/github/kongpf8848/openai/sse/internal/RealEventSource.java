package io.github.kongpf8848.openai.sse.internal;

import java.io.IOException;

import okhttp3.*;
import okhttp3.internal.connection.RealCall;
import io.github.kongpf8848.openai.sse.EventSource;
import io.github.kongpf8848.openai.sse.EventSourceListener;
import io.github.kongpf8848.openai.utils.StringUtils;
import org.jetbrains.annotations.NotNull;


public class RealEventSource implements EventSource, Callback {

    private final Request request;
    private final EventSourceListener listener;

    private RealCall call = null;
    private volatile boolean canceled = false;

    public RealEventSource(Request request, EventSourceListener listener) {
        this.request = request;
        this.listener = listener;
    }

    public void connect(OkHttpClient c) {
        OkHttpClient client = c.newBuilder()
                .eventListener(EventListener.NONE)
                .build();
        RealCall realCall = (RealCall) client.newCall(request);
        call = realCall;
        realCall.enqueue(this);
    }

    public void connect(Call.Factory callFactory) {
        RealCall realCall = (RealCall) callFactory.newCall(request);
        call = realCall;
        realCall.enqueue(this);
    }


    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        processResponse(response);
    }

    public void processResponse(Response response) {
        if (!response.isSuccessful()) {
            listener.onFailure(this, null, response);
            return;
        }

        ResponseBody body = response.body();

        if (!isEventStream(body)) {
            listener.onFailure(this, new IllegalStateException("Invalid content-type"+body.contentType()), response);
            return;
        }

        // This is a long-lived response. Cancel full-call timeouts.
        call.timeoutEarlyExit();

        // Replace the body with a stripped one so the callbacks can't see real data.
        //val response = response.stripBody()
        try {
            if (!canceled) {
                listener.onOpen(this, response);
                String currentLine = body.source().readUtf8Line();
                while (!canceled && currentLine != null) {
                    if ("data: [DONE]".equals(currentLine)) {
                        break;
                    }
                    if (currentLine.startsWith("data:")) {
                        String dataValue = currentLine.substring(5).trim();
                        if (!StringUtils.isEmpty(dataValue)) {
                            listener.onEvent(this, null, null, dataValue);
                        }
                    }
                    currentLine = body.source().readUtf8Line();
                }
            }
        } catch (Exception e) {
            Exception exception = canceled ? new IOException("canceled", e):e;
            listener.onFailure(this, exception, response);
            return;
        }
        if (canceled) {
            listener.onFailure(this, new IOException("canceled"), response);
        } else {
            listener.onClosed(this);
        }
    }

    private boolean isEventStream(ResponseBody responseBody) {
        MediaType contentType = responseBody.contentType();
        if (contentType == null) {
            return false;
        }
        return "text".equals(contentType.type()) && "event-stream".equals(contentType.subtype());
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        listener.onFailure(this, e, null);
    }


    @Override
    public Request request() {
        return request;
    }

    @Override
    public void cancel() {
        canceled = true;
        call.cancel();
    }


}
