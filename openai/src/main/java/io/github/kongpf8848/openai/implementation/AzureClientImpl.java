package io.github.kongpf8848.openai.implementation;

import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.io.IOException;

public final class AzureClientImpl {

    private final AzureClientImpl.AzureClientService service;
    private String deploymentId;
    private String apiVersion;

    public AzureClientImpl(Retrofit retrofit, String deploymentId, String apiVersion) {
        this.deploymentId=deploymentId;
        this.apiVersion=apiVersion;
        service = retrofit.create(AzureClientImpl.AzureClientService.class);
    }

    public Response<ChatCompletions> getChatCompletionsWithResponse(ChatCompletionsOptions chatCompletionsOptions) {
        try {
            return service.getChatCompletions(deploymentId,apiVersion,chatCompletionsOptions).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response<ResponseBody> getChatCompletionsWithResponseStream(ChatCompletionsOptions chatCompletionsOptions) {
        try {
            return service.getChatCompletionsStream(deploymentId,apiVersion,chatCompletionsOptions).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Observable<ChatCompletions> getChatCompletionsWithResponseAsync(ChatCompletionsOptions chatCompletionsOptions) {
        return service.getChatCompletionsAsync(deploymentId,apiVersion,chatCompletionsOptions);
    }

    public interface AzureClientService {

        @POST("openai/deployments/{deployment-id}/chat/completions")
        Call<ChatCompletions> getChatCompletions(
                @Path(value = "deployment-id") String deploymentId,
                @Query(value = "api-version") String apiVersion,
                @Body ChatCompletionsOptions chatCompletionsOptions
        );

        @POST("openai/deployments/{deployment-id}/chat/completions")
        @Streaming
        Call<ResponseBody> getChatCompletionsStream(
                @Path(value = "deployment-id") String deploymentId,
                @Query(value = "api-version") String apiVersion,
                @Body ChatCompletionsOptions chatCompletionsOptions
        );

        @POST("openai/deployments/{deployment-id}/chat/completions")
        Observable<ChatCompletions> getChatCompletionsAsync(
                @Path(value = "deployment-id") String deploymentId,
                @Query(value = "api-version") String apiVersion,
                @Body ChatCompletionsOptions chatCompletionsOptions
        );

    }
}
