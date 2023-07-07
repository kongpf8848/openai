package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.interceptor.OpenAIHeaderInterceptor;
import io.github.kongpf8848.openai.models.AzureKeyCredential;
import io.github.kongpf8848.openai.models.OpenAIKeyCredential;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class OpenAIClientBuilder {

    public static final String OPEN_AI_ENDPOINT = "https://api.openai.com/v1/";

    private boolean debug = true;

    public OpenAIClientBuilder debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    private OpenAIKeyCredential openAIKeyCredential;

    public OpenAIClientBuilder credential(OpenAIKeyCredential openAIKeyCredential) {
        this.openAIKeyCredential = openAIKeyCredential;
        return this;
    }

    private AzureKeyCredential azureKeyCredential;

    public OpenAIClientBuilder credential(AzureKeyCredential azureKeyCredential) {
        this.azureKeyCredential = azureKeyCredential;
        return this;
    }


    private String endpoint;

    public OpenAIClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    private AzureServiceVersion serviceVersion;

    public OpenAIClientBuilder serviceVersion(AzureServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
        return this;
    }

    private Retrofit retrofit;

    public OpenAIClientBuilder retrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
        return this;
    }

    public OpenAIClient buildClient() {
        return new OpenAIClient(buildInnerClient());
    }

    public OpenAIAsyncClient buildAsyncClient() {
        return new OpenAIAsyncClient(buildInnerClient());
    }


    private OpenAIClientImpl buildInnerClient() {
        Retrofit r = retrofit != null ? retrofit : createDefaultRetrofit();
        OpenAIClientImpl client = new OpenAIClientImpl(r);
        return client;
    }

    private Retrofit createDefaultRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(new OpenAIHeaderInterceptor(openAIKeyCredential.getKey()));
        if (debug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return new Retrofit.Builder()
                .baseUrl(OPEN_AI_ENDPOINT)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
