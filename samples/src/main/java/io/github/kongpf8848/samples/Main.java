package io.github.kongpf8848.samples;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.Completions;
import com.azure.ai.openai.models.CompletionsOptions;
import com.azure.ai.openai.models.NonAzureOpenAIKeyCredential;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CompletionsOptions options = new CompletionsOptions(Arrays.asList("hello"));
        options.setModel("text-davinci-003")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0)
                .setPresencePenalty(1.0);


        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(new NonAzureOpenAIKeyCredential("sk-xxxxxx"))
                .buildAsyncClient();
        Flux<Completions> flux = client.getCompletionsStream("text-davinci-003", options);
        flux.subscribe(new Consumer<Completions>() {
            @Override
            public void accept(Completions chatCompletions) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                System.out.println("=================onNext:" + Thread.currentThread().getName() + sdf.format(new Date()) + "--" + chatCompletions.getChoices().get(0).getText());

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("=================onError:" + throwable);
            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("=================onComplete");
            }
        });


        Thread.sleep(20000);
    }


}
