package io.github.kongpf8848.openai;

import com.azure.ai.openai.OpenAIClient;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;


public class OpenAI {

    public static Observable<ChatCompletions> getChatCompletions(ChatCompletionsOptions chatCompletionsOptions) {
        return RetrofitManager.getInstance().getOpenAIService()
                .chatCompletions(OpenAIConfig.getUrl(Constants.URL_CHAT_COMPLETIONS), chatCompletionsOptions)
                .subscribeOn(Schedulers.io());
    }

    public static void getChatCompletionsStream(ChatCompletionsOptions chatCompletionsOptions) {
        chatCompletionsOptions.setStream(true);
        Call<ResponseBody> call= RetrofitManager.getInstance().getOpenAIService()
                .chatCompletionsStream(OpenAIConfig.getUrl(Constants.URL_CHAT_COMPLETIONS), chatCompletionsOptions);
    }
}
