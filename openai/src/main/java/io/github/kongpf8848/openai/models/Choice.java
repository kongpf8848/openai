package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Choice {

    @JsonProperty(value = "text")
    private String text;

    @JsonProperty(value = "index")
    private int index;

    @JsonProperty(value = "logprobs")
    private CompletionsLogProbabilityModel logprobs;


    @JsonProperty(value = "finish_reason")
    private String finishReason;


    @JsonCreator
    private Choice(
            @JsonProperty(value = "text") String text,
            @JsonProperty(value = "index") int index,
            @JsonProperty(value = "logprobs") CompletionsLogProbabilityModel logprobs,
            @JsonProperty(value = "finish_reason") String finishReason) {
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
        this.finishReason = finishReason;
    }

    public String getText() {
        return this.text;
    }


    public int getIndex() {
        return this.index;
    }

    public CompletionsLogProbabilityModel getLogprobs() {
        return this.logprobs;
    }


    public String getFinishReason() {
        return this.finishReason;
    }
}
