package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class ChatCompletions {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "created")
    private int created;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "choices")
    private List<ChatChoice> choices;

    @JsonProperty(value = "usage")
    private CompletionsUsage usage;


    @JsonCreator
    private ChatCompletions(
            @JsonProperty(value = "id") String id,
            @JsonProperty(value = "object") String object,
            @JsonProperty(value = "created") int created,
            @JsonProperty(value = "model") String model,
            @JsonProperty(value = "choices") List<ChatChoice> choices,
            @JsonProperty(value = "usage") CompletionsUsage usage) {
        this.id = id;
        this.created = created;
        this.choices = choices;
        this.usage = usage;
    }


    public String getId() {
        return this.id;
    }

    public int getCreated() {
        return this.created;
    }

    public List<ChatChoice> getChoices() {
        return this.choices;
    }

    public CompletionsUsage getUsage() {
        return this.usage;
    }
}
