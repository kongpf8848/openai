package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIServerSentEvents;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class OpenAIAsyncClient {

    private final OpenAIClientImpl serviceClient;

    OpenAIAsyncClient(OpenAIClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    public Observable<ChatCompletions> getChatCompletions(ChatCompletionsOptions chatCompletionsOptions) {
        return serviceClient.getChatCompletionsWithResponseAsync(chatCompletionsOptions);
    }

    public Observable<ChatCompletions> getChatCompletionsStream(ChatCompletionsOptions chatCompletionsOptions) {
        chatCompletionsOptions.setStream(true);
        Response<ResponseBody> response = serviceClient.getChatCompletionsWithResponseStream(chatCompletionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<ChatCompletions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), ChatCompletions.class);
        return sse.getEvents();
    }
}
