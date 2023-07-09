package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.implementation.AzureClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIServerSentEvents;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.github.kongpf8848.openai.models.Completions;
import io.github.kongpf8848.openai.models.CompletionsOptions;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public final class OpenAIClient {

    private final OpenAIClientImpl openAIClient;
    private final AzureClientImpl azureClient;

    OpenAIClient(OpenAIClientImpl client) {
        this.openAIClient = client;
        this.azureClient = null;
    }

    OpenAIClient(AzureClientImpl client) {
        this.openAIClient = null;
        this.azureClient = client;
    }

    public Completions getCompletions(CompletionsOptions completionsOptions) {
        Response<Completions> response = openAIClient != null
                ? openAIClient.getCompletionsWithResponse(completionsOptions)
                :azureClient.getCompletionsWithResponse(completionsOptions);
        if (response == null) {
            return null;
        }
        return response.body();
    }

    public IterableStream<Completions> getCompletionsStream(CompletionsOptions completionsOptions) {
        completionsOptions.setStream(true);
        Response<ResponseBody> response = openAIClient!=null
                ? openAIClient.getCompletionsWithResponseStream(completionsOptions)
                : azureClient.getCompletionsWithResponseStream(completionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<Completions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), Completions.class);
        return new IterableStream<>(sse.getEvents());

    }

    public ChatCompletions getChatCompletions(ChatCompletionsOptions chatCompletionsOptions) {
        Response<ChatCompletions> response = openAIClient != null
                ? openAIClient.getChatCompletionsWithResponse(chatCompletionsOptions)
                :azureClient.getChatCompletionsWithResponse(chatCompletionsOptions);
        if (response == null) {
            return null;
        }
        return response.body();
    }

    public IterableStream<ChatCompletions> getChatCompletionsStream(ChatCompletionsOptions chatCompletionsOptions) {
        chatCompletionsOptions.setStream(true);
        Response<ResponseBody> response = openAIClient!=null
                ? openAIClient.getChatCompletionsWithResponseStream(chatCompletionsOptions)
                : azureClient.getChatCompletionsWithResponseStream(chatCompletionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<ChatCompletions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), ChatCompletions.class);
        return new IterableStream<>(sse.getEvents());

    }


}
