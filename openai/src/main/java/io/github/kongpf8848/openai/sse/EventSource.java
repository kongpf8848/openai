package io.github.kongpf8848.openai.sse;

import okhttp3.Request;

public interface EventSource {

    /** Returns the original request that initiated this event source. */
    Request request();

    /**
     * Immediately and violently release resources held by this event source. This does nothing if
     * the event source has already been closed or canceled.
     */
    void cancel();

    interface Factory {
        /**
         * Creates a new event source and immediately returns it. Creating an event source initiates an
         * asynchronous process to connect the socket. Once that succeeds or fails, `listener` will be
         * notified. The caller must cancel the returned event source when it is no longer in use.
         */
        EventSource newEventSource(Request request,EventSourceListener listener);
    }
}
