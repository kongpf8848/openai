package io.github.kongpf8848.openai;

public enum AzureServiceVersion {

    V2022_12_01("2022-12-01"),

    V2023_03_15_PREVIEW("2023-03-15-preview");

    private final String version;

    AzureServiceVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

}
