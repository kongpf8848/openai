package io.github.kongpf8848.openai.sse;

import okhttp3.Response;

public abstract class EventSourceListener {
    /**
     * Invoked when an event source has been accepted by the remote peer and may begin transmitting
     * events.
     */
    public void onOpen(EventSource eventSource, Response response) {
    }

    /**
     * TODO description.
     */
    public void onEvent(EventSource eventSource,String id, String type,String data) {
    }

    /**
     * TODO description.
     *
     * No further calls to this listener will be made.
     */
    public void onClosed(EventSource eventSource) {
    }

    /**
     * Invoked when an event source has been closed due to an error reading from or writing to the
     * network. Incoming events may have been lost. No further calls to this listener will be made.
     */
    public void onFailure(EventSource eventSource,Throwable t, Response response) {
    }
}
