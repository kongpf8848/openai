package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIServerSentEvents;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public final class OpenAIClient {

    private final OpenAIClientImpl serviceClient;

    OpenAIClient(OpenAIClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    public ChatCompletions getChatCompletions(ChatCompletionsOptions chatCompletionsOptions) {
        Response<ChatCompletions> response = serviceClient.getChatCompletionsWithResponse(chatCompletionsOptions);
        if (response == null) {
            return null;
        }
        return response.body();
    }

    public IterableStream<ChatCompletions> getChatCompletionsStream(ChatCompletionsOptions chatCompletionsOptions) {
        chatCompletionsOptions.setStream(true);
        Response<ResponseBody> response = serviceClient.getChatCompletionsWithResponseStream(chatCompletionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<ChatCompletions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), ChatCompletions.class);
        return new IterableStream<>(sse.getEvents());

    }



}
