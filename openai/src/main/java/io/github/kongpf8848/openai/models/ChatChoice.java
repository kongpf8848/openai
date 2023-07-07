package io.github.kongpf8848.openai.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChatChoice {

    @JsonProperty(value = "index")
    private int index;

    @JsonProperty(value = "message")
    private ChatMessage message;

    @JsonProperty(value = "finish_reason")
    private String finishReason;

    @JsonProperty(value = "delta")
    private ChatMessage delta;

    public int getIndex() {
        return this.index;
    }

    public ChatMessage getMessage() {
        return this.message;
    }

    public String getFinishReason() {
        return this.finishReason;
    }

    public ChatMessage getDelta() {
        return this.delta;
    }

}
