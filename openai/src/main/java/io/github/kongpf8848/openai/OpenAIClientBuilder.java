package io.github.kongpf8848.openai;

import io.github.kongpf8848.openai.implementation.AzureClientImpl;
import io.github.kongpf8848.openai.implementation.OpenAIClientImpl;
import io.github.kongpf8848.openai.interceptor.AzureHeaderInterceptor;
import io.github.kongpf8848.openai.interceptor.OpenAIHeaderInterceptor;
import io.github.kongpf8848.openai.models.AzureKeyCredential;
import io.github.kongpf8848.openai.models.OpenAIKeyCredential;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class OpenAIClientBuilder {

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

    private String deploymentId;

    public OpenAIClientBuilder deploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
        return this;
    }

    private AzureApiVersion apiVersion=AzureApiVersion.V2023_03_15_PREVIEW;

    public OpenAIClientBuilder apiVersion(AzureApiVersion apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    private Retrofit retrofit;

    public OpenAIClientBuilder retrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
        return this;
    }

    public OpenAIClient buildClient() {
        if(azureKeyCredential!=null){
            return new OpenAIClient(buildInnerAzureClient());
        }
        return new OpenAIClient(buildInnerClient());
    }

    public OpenAIAsyncClient buildAsyncClient() {
        if(azureKeyCredential!=null){
            return new OpenAIAsyncClient(buildInnerAzureClient());
        }
        return new OpenAIAsyncClient(buildInnerClient());
    }


    private OpenAIClientImpl buildInnerClient() {
        Retrofit r = retrofit != null ? retrofit : createRetrofit(OpenAIConstants.OPEN_AI_ENDPOINT,new OpenAIHeaderInterceptor(openAIKeyCredential.getKey()));
        OpenAIClientImpl client = new OpenAIClientImpl(r);
        return client;
    }

    private AzureClientImpl buildInnerAzureClient() {
        Retrofit r = retrofit != null ? retrofit : createRetrofit(endpoint,new AzureHeaderInterceptor(azureKeyCredential.getKey()));
        AzureClientImpl client = new AzureClientImpl(r,deploymentId,apiVersion.getVersion());
        return client;
    }

    private Retrofit createRetrofit(String endpoint, Interceptor authInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(authInterceptor);
        if (debug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}
