package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CompletionsOptions {

    @JsonProperty(value = "prompt")
    private List<String> prompt;

    @JsonProperty(value = "max_tokens")
    private Integer maxTokens;

    @JsonProperty(value = "temperature")
    private Double temperature;

    @JsonProperty(value = "top_p")
    private Double topP;

    @JsonProperty(value = "logit_bias")
    private Map<String, Integer> logitBias;

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "n")
    private Integer n;

    @JsonProperty(value = "logprobs")
    private Integer logprobs;

    @JsonProperty(value = "echo")
    private Boolean echo;

    @JsonProperty(value = "stop")
    private List<String> stop;

    @JsonProperty(value = "presence_penalty")
    private Double presencePenalty;

    @JsonProperty(value = "frequency_penalty")
    private Double frequencyPenalty;

    @JsonProperty(value = "best_of")
    private Integer bestOf;

    @JsonProperty(value = "stream")
    private Boolean stream;

    @JsonProperty(value = "model")
    private String model;

    @JsonCreator
    public CompletionsOptions(@JsonProperty(value = "prompt") List<String> prompt) {
        this.prompt = prompt;
    }

    public List<String> getPrompt() {
        return this.prompt;
    }

    public Integer getMaxTokens() {
        return this.maxTokens;
    }

    public CompletionsOptions setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public CompletionsOptions setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Double getTopP() {
        return this.topP;
    }

    public CompletionsOptions setTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    public Map<String, Integer> getLogitBias() {
        return this.logitBias;
    }

    public CompletionsOptions setLogitBias(Map<String, Integer> logitBias) {
        this.logitBias = logitBias;
        return this;
    }

    public String getUser() {
        return this.user;
    }

    public CompletionsOptions setUser(String user) {
        this.user = user;
        return this;
    }

    public Integer getN() {
        return this.n;
    }

    public CompletionsOptions setN(Integer n) {
        this.n = n;
        return this;
    }

    public Integer getLogprobs() {
        return this.logprobs;
    }

    public CompletionsOptions setLogprobs(Integer logprobs) {
        this.logprobs = logprobs;
        return this;
    }

    public Boolean isEcho() {
        return this.echo;
    }

    public CompletionsOptions setEcho(Boolean echo) {
        this.echo = echo;
        return this;
    }

    public List<String> getStop() {
        return this.stop;
    }

    public CompletionsOptions setStop(List<String> stop) {
        this.stop = stop;
        return this;
    }

    public Double getPresencePenalty() {
        return this.presencePenalty;
    }

    public CompletionsOptions setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    public Double getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    public CompletionsOptions setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }


    public Integer getBestOf() {
        return this.bestOf;
    }

    public CompletionsOptions setBestOf(Integer bestOf) {
        this.bestOf = bestOf;
        return this;
    }

    public Boolean isStream() {
        return this.stream;
    }

    public CompletionsOptions setStream(Boolean stream) {
        this.stream = stream;
        return this;
    }

    public String getModel() {
        return this.model;
    }

    public CompletionsOptions setModel(String model) {
        this.model = model;
        return this;
    }
}