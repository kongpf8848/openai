package io.github.kongpf8848.openai.implementation;

import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;

public final class OpenAIClientImpl {

    private final OpenAIClientImpl.OpenAIClientService service;

    public OpenAIClientImpl(Retrofit retrofit){
        service = retrofit.create(OpenAIClientService.class);
    }

   public Response<ChatCompletions> getChatCompletionsWithResponse(ChatCompletionsOptions chatCompletionsOptions) {
       try {
           return service.getChatCompletions(chatCompletionsOptions).execute();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

    public Observable<ChatCompletions> getChatCompletionsWithResponseAsync(ChatCompletionsOptions chatCompletionsOptions) {
        return service.getChatCompletionsAsync(chatCompletionsOptions).subscribeOn(Schedulers.io());
    }

    public interface OpenAIClientService {

        @POST("chat/completions")
        Call<ChatCompletions> getChatCompletions(@Body ChatCompletionsOptions chatCompletionsOptions);

        @POST("chat/completions")
        Observable<ChatCompletions> getChatCompletionsAsync(@Body ChatCompletionsOptions chatCompletionsOptions);

    }
}
