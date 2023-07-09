package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class CompletionsLogProbabilityModel {

    @JsonProperty(value = "tokens")
    private List<String> tokens;

    @JsonProperty(value = "token_logprobs")
    private List<Double> tokenLogprobs;


    @JsonProperty(value = "top_logprobs")
    private List<Map<String, Double>> topLogprobs;


    @JsonProperty(value = "text_offset")
    private List<Integer> textOffset;


    @JsonCreator
    private CompletionsLogProbabilityModel(
            @JsonProperty(value = "tokens") List<String> tokens,
            @JsonProperty(value = "token_logprobs") List<Double> tokenLogprobs,
            @JsonProperty(value = "top_logprobs") List<Map<String, Double>> topLogprobs,
            @JsonProperty(value = "text_offset") List<Integer> textOffset) {
        this.tokens = tokens;
        this.tokenLogprobs = tokenLogprobs;
        this.topLogprobs = topLogprobs;
        this.textOffset = textOffset;
    }

    public List<String> getTokens() {
        return this.tokens;
    }


    public List<Double> getTokenLogprobs() {
        return this.tokenLogprobs;
    }

    public List<Map<String, Double>> getTopLogprobs() {
        return this.topLogprobs;
    }

    public List<Integer> getTextOffset() {
        return this.textOffset;
    }
}