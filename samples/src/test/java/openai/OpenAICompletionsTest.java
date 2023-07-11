package openai;

import io.github.kongpf8848.openai.OpenAIAsyncClient;
import io.github.kongpf8848.openai.OpenAIClient;
import io.github.kongpf8848.openai.OpenAIClientBuilder;
import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.models.Completions;
import io.github.kongpf8848.openai.models.CompletionsOptions;
import io.github.kongpf8848.openai.models.OpenAIKeyCredential;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class OpenAICompletionsTest {

    @Test
    public void testOpenAICompletions() {
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new OpenAIKeyCredential(Constants.OPENAI_API_KEY))
                .buildClient();

        CompletionsOptions options = new CompletionsOptions(Arrays.asList("hello"));
        options.setModel("text-davinci-003")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        Completions completions = client.getCompletions(options);
        if (completions != null) {
            System.out.println("========"+ completions.getChoices().get(0).getText());
        }
    }

    @Test
    public void testOpenAICompletionsStream() {
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new OpenAIKeyCredential(Constants.OPENAI_API_KEY))
                .buildClient();

        CompletionsOptions options = new CompletionsOptions(Arrays.asList("hello"));
        options.setModel("text-davinci-003")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        IterableStream<Completions> completionsStream = client.getCompletionsStream(options);
        completionsStream.forEach(completions -> {
            System.out.println("========" + completions.getChoices().get(0).getText());
        });
    }


    @Test
    public void testOpenAICompletionsAsync() {
        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(new OpenAIKeyCredential(Constants.OPENAI_API_KEY))
                .buildAsyncClient();

        CompletionsOptions options = new CompletionsOptions(Arrays.asList("hello"));
        options.setModel("text-davinci-003")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        Observable<Completions> observable = client.getCompletions(options);
        observable.subscribe(new Observer<>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("========onSubscribe========");
            }

            @Override
            public void onNext(Completions completions) {
                System.out.println("============="+Thread.currentThread().getName());
                System.out.println("========onNext:" + completions.getChoices().get(0).getText());
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
    public void testOpenAICompletionsStreamAsync() {
        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(new OpenAIKeyCredential(Constants.OPENAI_API_KEY))
                .buildAsyncClient();

        CompletionsOptions options = new CompletionsOptions(Arrays.asList("hello"));
        options.setModel("text-davinci-003")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0);
        Observable<Completions> observable = client.getCompletionsStream(options);
        observable.subscribe(new Observer<Completions>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("========onSubscribe========");
            }

            @Override
            public void onNext(Completions completions) {
                System.out.println("========onNext:" + Thread.currentThread().getName());
                System.out.println("========onNext:" + completions.getChoices().get(0).getText());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("========onError:"+e);
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

    @Test
    public void config(){
        System.out.println("========OPENAI_API_KEY:"+Constants.OPENAI_API_KEY);
        System.out.println("========AZURE_API_KEY:"+Constants.AZURE_API_KEY);
        System.out.println("========AZURE_END_POINT:"+Constants.AZURE_END_POINT);
        System.out.println("========AZURE_DEPLOYMENT_ID:"+Constants.AZURE_DEPLOYMENT_ID);
    }


}
