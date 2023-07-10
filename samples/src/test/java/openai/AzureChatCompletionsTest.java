package openai;

import io.github.kongpf8848.openai.AzureApiVersion;
import io.github.kongpf8848.openai.OpenAIAsyncClient;
import io.github.kongpf8848.openai.OpenAIClient;
import io.github.kongpf8848.openai.OpenAIClientBuilder;
import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.models.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AzureChatCompletionsTest {

    @Test
    public OpenAIClient getOpenAIClient() {
        return new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(Constants.AZURE_API_KEY))
                .endpoint(Constants.AZURE_END_POINT)
                .deploymentId(Constants.AZURE_DEPLOYMENT_ID)
                .apiVersion(AzureApiVersion.V2023_03_15_PREVIEW)
                .buildClient();
    }

    @Test
    public OpenAIAsyncClient getOpenAIAsyncClient() {
        return new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(Constants.AZURE_API_KEY))
                .endpoint(Constants.AZURE_END_POINT)
                .deploymentId(Constants.AZURE_DEPLOYMENT_ID)
                .apiVersion(AzureApiVersion.V2023_03_15_PREVIEW)
                .buildAsyncClient();
    }


    @Test
    public void testAzureChatCompletions() {

        List<ChatMessage> list = new ArrayList<>();
        list.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(list);
        options.setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        ChatCompletions completions = getOpenAIClient().getChatCompletions(options);
        if (completions != null) {
            System.out.println("========" + completions.getChoices().get(0).getMessage().getContent());
        }
    }

    @Test
    public void testAzureChatCompletionsStream() {

        List<ChatMessage> list = new ArrayList<>();
        list.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(list);
        options.setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        IterableStream<ChatCompletions> completionsStream = getOpenAIClient().getChatCompletionsStream(options);
        completionsStream.forEach(completions -> {
            System.out.println("========" + completions.getChoices().get(0).getDelta().getContent());
        });
    }


    @Test
    public void testAzureChatCompletionsAsync() {

        List<ChatMessage> list = new ArrayList<>();
        list.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(list);
        options.setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        Observable<ChatCompletions> observable = getOpenAIAsyncClient().getChatCompletions(options);
        observable.subscribe(new Observer<>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("========onSubscribe========");
            }

            @Override
            public void onNext(ChatCompletions completions) {
                System.out.println("=============" + Thread.currentThread().getName());
                System.out.println("========onNext:" + completions.getChoices().get(0).getMessage().getContent());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("========onError:" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("========onComplete========");
            }
        });

    }

    @Test
    public void testAzureChatCompletionsStreamAsync() {

        List<ChatMessage> list = new ArrayList<>();
        list.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(list);
        options.setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        Observable<ChatCompletions> observable = getOpenAIAsyncClient().getChatCompletionsStream(options);
        observable.subscribe(new Observer<ChatCompletions>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("========onSubscribe========");
            }

            @Override
            public void onNext(ChatCompletions completions) {
                System.out.println("========onNext:" + completions.getChoices().get(0).getDelta().getContent());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("========onError:" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("========onComplete========");
            }
        });

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
