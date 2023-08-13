package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public final class ChatFunction {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value="description")
    private String description;

    @JsonProperty(value="description")
    private JsonNode parameters;

    public void setName(String name) {
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description =description;
    }

    public String getDescription() {
        return description;
    }

    public void setParameters(JsonNode parameters) {
        this.parameters =parameters;
    }

    public JsonNode getParameters() {
        return parameters;
    }

}
