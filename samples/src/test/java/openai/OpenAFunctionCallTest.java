package openai;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import io.github.kongpf8848.openai.OpenAIAsyncClient;
import io.github.kongpf8848.openai.OpenAIClient;
import io.github.kongpf8848.openai.OpenAIClientBuilder;
import io.github.kongpf8848.openai.core.IterableStream;
import io.github.kongpf8848.openai.models.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenAFunctionCallTest {

    @Test
    public void testFunction() {
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new OpenAIKeyCredential(Constants.OPENAI_API_KEY))
                .buildClient();

        List<ChatMessage> chatMessageList = new ArrayList<>();
        chatMessageList.add(new ChatMessage("user").setContent("What is the weather like in Boston?"));

        ChatCompletionsOptions options=new ChatCompletionsOptions(chatMessageList);

        List<ChatFunction> chatFunctionList=new ArrayList<>();
        ChatFunction chatFunction=new ChatFunction();
        chatFunction.setName("get_current_weather");
        chatFunction.setDescription("Get the current weather in a given location");

    }



}
