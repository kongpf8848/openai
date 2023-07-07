package io.github.kongpf8848.openai.implementation;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Observable;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class OpenAIServerSentEvents<T> {

    private final Observable<BufferedSource> source;
    private final Class<T> type;

    private static final ObjectMapper SERIALIZER = new ObjectMapper()
            .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);


    public OpenAIServerSentEvents(Observable<BufferedSource> source, Class<T> type) {
        this.source = source;
        this.type = type;
    }

    public Observable<T> getEvents(){
        return source.concatMap(bufferedSource -> {
            List<T> values = new ArrayList<>();
            try {
                String currentLine=bufferedSource.readUtf8Line();
                while(currentLine!=null){
                    if ("data: [DONE]".equals(currentLine)) {
                        return Observable.fromIterable(values);
                    }
                    if(currentLine.startsWith("data:")){
                        String dataValue = currentLine.substring(5).trim();
                        if (!dataValue.isEmpty() && isValidJson(dataValue)) {
                            T value = SERIALIZER.readValue(dataValue, type);
                            values.add(value);
                        }
                    }
                    currentLine= bufferedSource.readUtf8Line();
                }
                return Observable.fromIterable(values);
            } catch (IOException e) {
                e.printStackTrace();
                return Observable.error(e);
            }
        });
    }

    private static boolean isValidJson(String json) {
        try {
            SERIALIZER.readTree(json);
            return true;
        } catch (JacksonException exception) {
            return false;
        }
    }
}
