package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public final class ChatFunctionCall {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value="arguments")
    private JsonNode arguments;

    public String getName() {
        return name;
    }

    public JsonNode getArguments() {
        return arguments;
    }
}
