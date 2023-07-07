package io.github.kongpf8848.openai;

public enum AzureApiVersion {

    V2022_12_01("2022-12-01"),

    V2023_03_15_PREVIEW("2023-03-15-preview"),

    V2023_05_15("2023-05-15"),

    V2023_06_01_PREVIEW("2023-06-01-preview");

    private final String version;

    AzureApiVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

}
