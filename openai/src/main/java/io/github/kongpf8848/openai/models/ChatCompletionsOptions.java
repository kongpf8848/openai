package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ChatCompletionsOptions {

    @JsonProperty(value = "messages")
    private List<ChatMessage> messages;

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

    @JsonProperty(value = "stop")
    private List<String> stop;

    @JsonProperty(value = "presence_penalty")
    private Double presencePenalty;

    @JsonProperty(value = "frequency_penalty")
    private Double frequencyPenalty;

    @JsonProperty(value = "stream")
    private Boolean stream;

    @JsonProperty(value = "model")
    private String model;


    @JsonCreator
    public ChatCompletionsOptions(@JsonProperty(value = "messages") List<ChatMessage> messages) {
        this.messages = messages;
    }

    public List<ChatMessage> getMessages() {
        return this.messages;
    }

    public Integer getMaxTokens() {
        return this.maxTokens;
    }

    public ChatCompletionsOptions setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public ChatCompletionsOptions setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Double getTopP() {
        return this.topP;
    }

    public ChatCompletionsOptions setTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    public Map<String, Integer> getLogitBias() {
        return this.logitBias;
    }

    public ChatCompletionsOptions setLogitBias(Map<String, Integer> logitBias) {
        this.logitBias = logitBias;
        return this;
    }

    public String getUser() {
        return this.user;
    }

    public ChatCompletionsOptions setUser(String user) {
        this.user = user;
        return this;
    }

    public Integer getN() {
        return this.n;
    }

    public ChatCompletionsOptions setN(Integer n) {
        this.n = n;
        return this;
    }

    public List<String> getStop() {
        return this.stop;
    }

    public ChatCompletionsOptions setStop(List<String> stop) {
        this.stop = stop;
        return this;
    }

    public Double getPresencePenalty() {
        return this.presencePenalty;
    }

    public ChatCompletionsOptions setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    public Double getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    public ChatCompletionsOptions setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    public Boolean isStream() {
        return this.stream;
    }

    public ChatCompletionsOptions setStream(Boolean stream) {
        this.stream = stream;
        return this;
    }

    public String getModel() {
        return this.model;
    }

    public ChatCompletionsOptions setModel(String model) {
        this.model = model;
        return this;
    }
}
