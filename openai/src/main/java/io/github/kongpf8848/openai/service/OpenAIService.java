package io.github.kongpf8848.openai.service;

import io.github.kongpf8848.openai.Constants;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface OpenAIService {

    @POST
    Observable<ChatCompletions> chatCompletions(@Url String url, @Body ChatCompletionsOptions chatCompletionsOptions);

    @POST
    @Streaming
    Call<ResponseBody> chatCompletionsStream(@Url String url, @Body ChatCompletionsOptions chatCompletionsOptions);
}
