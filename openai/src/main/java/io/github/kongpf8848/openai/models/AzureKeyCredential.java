package io.github.kongpf8848.openai.models;

import java.util.Objects;

public class AzureKeyCredential {

    private String key;

    public AzureKeyCredential(String key) {
        Objects.requireNonNull(key, "'key' cannot be null.");
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
