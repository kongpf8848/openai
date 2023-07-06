package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CompletionsUsage {

    @JsonProperty(value = "completion_tokens")
    private int completionTokens;

    @JsonProperty(value = "prompt_tokens")
    private int promptTokens;

    @JsonProperty(value = "total_tokens")
    private int totalTokens;


    @JsonCreator
    private CompletionsUsage(
            @JsonProperty(value = "completion_tokens") int completionTokens,
            @JsonProperty(value = "prompt_tokens") int promptTokens,
            @JsonProperty(value = "total_tokens") int totalTokens) {
        this.completionTokens = completionTokens;
        this.promptTokens = promptTokens;
        this.totalTokens = totalTokens;
    }

    public int getCompletionTokens() {
        return this.completionTokens;
    }

    public int getPromptTokens() {
        return this.promptTokens;
    }

    public int getTotalTokens() {
        return this.totalTokens;
    }
}
