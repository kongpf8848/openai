package io.github.kongpf8848.samples;

import io.github.kongpf8848.openai.AzureApiVersion;
import io.github.kongpf8848.openai.OpenAIAsyncClient;
import io.github.kongpf8848.openai.OpenAIClientBuilder;
import io.github.kongpf8848.openai.models.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        testAzureStream();

        Thread.sleep(20000);
    }


    private static void testAzureStream(){
        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential("xxx"))
                .endpoint("https://xxx.openai.azure.com/")
                .deploymentId("xxx")
                .apiVersion(AzureApiVersion.V2023_03_15_PREVIEW)
                .buildAsyncClient();

        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessageList).setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0)
                .setPresencePenalty(1.0);
        Observable<ChatCompletions> observable = client.getChatCompletionsStream(options);
        observable.subscribe(new Observer<ChatCompletions>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("=============onSubscribe:"+d);
            }

            @Override
            public void onNext(ChatCompletions chatCompletions) {
                System.out.println("=============onNext:"+ chatCompletions.getChoices().get(0).getDelta().getContent());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("=============onError:"+e);
            }

            @Override
            public void onComplete() {
                System.out.println("=============onComplete");
            }
        });

    }
}
