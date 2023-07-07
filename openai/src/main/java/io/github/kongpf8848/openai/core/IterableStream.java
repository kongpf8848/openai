package io.github.kongpf8848.openai.core;

import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IterableStream<T> implements Iterable<T> {

    private static final int DEFAULT_BATCH_SIZE = 1;
    private static final IterableStream<Object> EMPTY = new IterableStream<>(new ArrayList<>());

    private final Observable<T> observable;
    private final Iterable<T> iterable;


    public IterableStream(Observable<T> observable) {
        this.observable = Objects.requireNonNull(observable, "'observable' cannot be null.");
        this.iterable = null;
    }

    public IterableStream(Iterable<T> iterable) {
        this.iterable = Objects.requireNonNull(iterable, "'iterable' cannot be null.");
        this.observable = null;
    }

    public Stream<T> stream() {
        return (observable != null)
                ? StreamSupport.stream(observable.blockingIterable(DEFAULT_BATCH_SIZE).spliterator(), false)
                : StreamSupport.stream(iterable.spliterator(), false);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return (observable != null)
                ? observable.blockingIterable(DEFAULT_BATCH_SIZE).iterator()
                : iterable.iterator();
    }

    public static <T> IterableStream<T> of(Iterable<T> iterable) {
        if (iterable == null) {
            return (IterableStream<T>) EMPTY;
        } else {
            return new IterableStream<T>(iterable);
        }
    }
}
