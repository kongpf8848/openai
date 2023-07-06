package io.github.kongpf8848.samples;

import io.github.kongpf8848.openai.Constants;
import io.github.kongpf8848.openai.OpenAI;
import io.github.kongpf8848.openai.OpenAIConfig;
import io.github.kongpf8848.openai.models.ChatCompletions;
import io.github.kongpf8848.openai.models.ChatCompletionsOptions;
import io.github.kongpf8848.openai.models.ChatMessage;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(new ChatMessage("user").setContent("hello"));
        ChatCompletionsOptions options = new ChatCompletionsOptions(chatMessageList);
        options.setModel("gpt-3.5-turbo")
                .setMaxTokens(1000)
                .setTemperature(0.8)
                .setTopP(1.0)
                .setPresencePenalty(1.0);
        OpenAI.getChatCompletions(options).subscribe(new Observer<ChatCompletions>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("===========onSubscribe");
            }

            @Override
            public void onNext(ChatCompletions chatCompletions) {
                System.out.println("===========onNext:" + Thread.currentThread().getName()
                        + "," + chatCompletions.getChoices().get(0).getMessage().getContent());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("===========onError:" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("===========onComplete");
            }
        });
        Thread.sleep(10000);
    }
}
