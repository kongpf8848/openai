package io.github.kongpf8848.openai;

import com.azure.core.util.serializer.JacksonAdapter;
import io.github.kongpf8848.openai.interceptor.AuthorizationInterceptor;
import io.github.kongpf8848.openai.service.OpenAIService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitManager {

    protected OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private OpenAIService openAIService;


    private static final class InstanceHolder {
        static final RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return InstanceHolder.instance;
    }

    private RetrofitManager() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);

        builder.addInterceptor(new AuthorizationInterceptor());
        if (OpenAIConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(OpenAIConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        openAIService = retrofit.create(OpenAIService.class);

    }

    public OpenAIService getOpenAIService(){
        return openAIService;
    }



}
