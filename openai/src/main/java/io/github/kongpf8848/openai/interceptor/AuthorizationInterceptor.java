package io.github.kongpf8848.openai.interceptor;

import io.github.kongpf8848.openai.Constants;
import io.github.kongpf8848.openai.OpenAIConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        String authKey="",authValue="";
        if(OpenAIConfig.API_TYPE== Constants.TYPE_OPENAI){
            authKey="Authorization";
            authValue="Bearer "+OpenAIConfig.OPENAI_API_KEY;
        }else if(OpenAIConfig.API_TYPE==Constants.TYPE_AZURE){
            authKey="api-key";
            authValue=OpenAIConfig.AZURE_API_KEY;
        }
        Request request=chain.request();
        Request newRequest=request.newBuilder()
                .addHeader(authKey,authValue)
                .addHeader("Content-Type","application/json")
                .build();
        return chain.proceed(newRequest);
    }
}
