package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.implementation.AzureClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIServerSentEvents;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.github.kongpf8848.openai.models.Completions;
import io.github.kongpf8848.openai.models.CompletionsOptions;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class OpenAIAsyncClient {

    private final OpenAIClientImpl openAIClient;
    private final AzureClientImpl azureClient;

    OpenAIAsyncClient(OpenAIClientImpl client) {
        this.openAIClient = client;
        this.azureClient = null;
    }

    OpenAIAsyncClient(AzureClientImpl client) {
        this.openAIClient = null;
        this.azureClient = client;
    }

    public Observable<Completions> getCompletions(CompletionsOptions completionsOptions) {
        return openAIClient != null
                ? openAIClient.getCompletionsWithResponseAsync(completionsOptions)
                : azureClient.getCompletionsWithResponseAsync(completionsOptions);
    }

    public Observable<Completions> getCompletionsStream(CompletionsOptions completionsOptions) {
        completionsOptions.setStream(true);
        Response<ResponseBody> response = openAIClient != null
                ? openAIClient.getCompletionsWithResponseStream(completionsOptions)
                : azureClient.getCompletionsWithResponseStream(completionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<Completions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), Completions.class);
        return sse.getEvents();
    }

    public Observable<ChatCompletions> getChatCompletions(ChatCompletionsOptions chatCompletionsOptions) {
        return openAIClient != null
                ? openAIClient.getChatCompletionsWithResponseAsync(chatCompletionsOptions)
                : azureClient.getChatCompletionsWithResponseAsync(chatCompletionsOptions);
    }

    public Observable<ChatCompletions> getChatCompletionsStream(ChatCompletionsOptions chatCompletionsOptions) {
        chatCompletionsOptions.setStream(true);
        Response<ResponseBody> response = openAIClient != null
                ? openAIClient.getChatCompletionsWithResponseStream(chatCompletionsOptions)
                : azureClient.getChatCompletionsWithResponseStream(chatCompletionsOptions);
        if (response == null || response.body() == null) {
            return null;
        }
        OpenAIServerSentEvents<ChatCompletions> sse = new OpenAIServerSentEvents<>(Observable.just(response.body().source()), ChatCompletions.class);
        return sse.getEvents();
    }
}
