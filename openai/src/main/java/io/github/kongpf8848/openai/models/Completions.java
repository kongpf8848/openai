package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class Completions {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "created")
    private int created;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "choices")
    private List<Choice> choices;

    @JsonProperty(value = "usage")
    private CompletionsUsage usage;


    @JsonCreator
    private Completions(
            @JsonProperty(value = "id") String id,
            @JsonProperty(value = "object") String object,
            @JsonProperty(value = "created") int created,
            @JsonProperty(value = "model") String model,
            @JsonProperty(value = "choices") List<Choice> choices,
            @JsonProperty(value = "usage") CompletionsUsage usage) {
        this.id = id;
        this.object=object;
        this.created = created;
        this.model=model;
        this.choices = choices;
        this.usage = usage;
    }


    public String getId() {
        return this.id;
    }

    public String getObject() {
        return this.object;
    }

    public int getCreated() {
        return this.created;
    }

    public String getModel() {
        return this.model;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }


    public CompletionsUsage getUsage() {
        return this.usage;
    }
}
