package io.github.kongpf8848.openai.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpenAIHeaderInterceptor implements Interceptor {

    private String key;

    public OpenAIHeaderInterceptor(String key) {
        this.key = key;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer " + key)
                .addHeader("Content-Type", "application/json")
                .build();
        return chain.proceed(newRequest);
    }
}
