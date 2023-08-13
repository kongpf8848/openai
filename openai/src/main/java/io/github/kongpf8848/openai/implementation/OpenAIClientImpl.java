package io.github.kongpf8848.openai.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.kongpf8848.openai.OpenAIConstants;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.github.kongpf8848.openai.models.Completions;
import io.github.kongpf8848.openai.models.CompletionsOptions;
import io.github.kongpf8848.openai.sse.EventSource;
import io.github.kongpf8848.openai.sse.EventSourceListener;
import io.github.kongpf8848.openai.sse.EventSources;
import io.github.kongpf8848.openai.utils.JacksonUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

public final class OpenAIClientImpl {

    private final Retrofit retrofit;
    private final OpenAIClientService service;

    public OpenAIClientImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
        service = retrofit.create(OpenAIClientService.class);
    }

    public Response<Completions> getCompletionsWithResponse(CompletionsOptions completionsOptions) {
        try {
            return service.getCompletions(completionsOptions).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Observable<Completions> getCompletionsWithResponseStream(CompletionsOptions completionsOptions) {
        return getCompletionsObservable(completionsOptions,"completions",Completions.class);
    }

    public Observable<Completions> getCompletionsWithResponseAsync(CompletionsOptions completionsOptions) {
        return service.getCompletionsAsync(completionsOptions);
    }

    public Response<ChatCompletions> getChatCompletionsWithResponse(ChatCompletionsOptions chatCompletionsOptions) {
        try {
            return service.getChatCompletions(chatCompletionsOptions).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Observable<ChatCompletions> getChatCompletionsWithResponseStream(ChatCompletionsOptions chatCompletionsOptions) {
        return getCompletionsObservable(chatCompletionsOptions,"chat/completions",ChatCompletions.class);
    }


    public Observable<ChatCompletions> getChatCompletionsWithResponseAsync(ChatCompletionsOptions chatCompletionsOptions) {
        return service.getChatCompletionsAsync(chatCompletionsOptions);
    }

    private <T> Observable<T> getCompletionsObservable(Object object,String relativeUrl, Type returnType) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {

                ObjectMapper mapper = JacksonUtils.defaultObjectMapper();
                JavaType javaType = mapper.getTypeFactory().constructType(object.getClass());
                ObjectWriter writer = mapper.writerFor(javaType);
                byte[] bytes = writer.writeValueAsBytes(object);


                Request request = new Request.Builder()
                        .url(OpenAIConstants.OPEN_AI_ENDPOINT+relativeUrl)
                        .post(RequestBody.create(bytes, MediaType.parse("application/json")))
                        .build();

                EventSources.createFactory(retrofit.callFactory()).newEventSource(request, new EventSourceListener() {
                    @Override
                    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
                        super.onEvent(eventSource, id, type, data);
                        JavaType javaType = mapper.getTypeFactory().constructType(returnType);
                        ObjectReader reader = mapper.readerFor(javaType);
                        try {
                            T t = reader.readValue(data);
                            emitter.onNext(t);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable okhttp3.Response response) {
                        super.onFailure(eventSource, t, response);
                        System.out.println("============onFailure:" + t);
                        emitter.onError(t);
                    }

                    @Override
                    public void onClosed(@NotNull EventSource eventSource) {
                        super.onClosed(eventSource);
                        System.out.println("============onClosed");
                        emitter.onComplete();
                    }
                });
            }
        });
    }

    public interface OpenAIClientService {

        //completions
        @POST("completions")
        Call<Completions> getCompletions(@Body CompletionsOptions completionsOptions);

        @POST("completions")
        Observable<Completions> getCompletionsAsync(@Body CompletionsOptions completionsOptions);

        //chat/completions
        @POST("chat/completions")
        Call<ChatCompletions> getChatCompletions(@Body ChatCompletionsOptions chatCompletionsOptions);

        @POST("chat/completions")
        Observable<ChatCompletions> getChatCompletionsAsync(@Body ChatCompletionsOptions chatCompletionsOptions);

    }
}
