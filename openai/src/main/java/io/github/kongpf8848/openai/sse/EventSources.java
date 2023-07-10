package io.github.kongpf8848.openai.sse;

import io.github.kongpf8848.openai.sse.internal.RealEventSource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventSources {

    public static EventSource.Factory createFactory(OkHttpClient client){
        return new EventSource.Factory(){
            @Override
            public EventSource newEventSource(Request request, EventSourceListener listener) {
                Request actualRequest =(request.header("Accept") == null)
                    ? request.newBuilder().addHeader("Accept", "text/event-stream").build()
                    : request;
                RealEventSource eventSource=new RealEventSource(actualRequest, listener);
                eventSource.connect(client);
                return eventSource;
            }
        };
    }

    public static void processResponse(Response response, EventSourceListener listener) {
        RealEventSource eventSource = new RealEventSource(response.request(), listener);
        eventSource.processResponse(response);
    }
}
