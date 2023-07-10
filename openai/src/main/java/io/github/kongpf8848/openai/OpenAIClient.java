package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.implementation.AzureClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.github.kongpf8848.openai.models.Completions;
import io.github.kongpf8848.openai.models.CompletionsOptions;
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
        return openAIClient!=null
                ? new IterableStream(openAIClient.getCompletionsWithResponseStream(completionsOptions))
                : new IterableStream(azureClient.getCompletionsWithResponseStream(completionsOptions));

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
        return openAIClient!=null
                ? new IterableStream<>(openAIClient.getChatCompletionsWithResponseStream(chatCompletionsOptions))
                : new IterableStream<>(azureClient.getChatCompletionsWithResponseStream(chatCompletionsOptions));

    }


}
