package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChatMessage {

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "function_call")
    private ChatFunctionCall function_call;


    @JsonCreator
    public ChatMessage(@JsonProperty(value = "role") String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public String getContent() {
        return this.content;
    }

    public ChatMessage setContent(String content) {
        this.content = content;
        return this;
    }
}
