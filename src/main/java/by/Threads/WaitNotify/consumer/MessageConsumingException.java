package by.Threads.WaitNotify.consumer;

public final class MessageConsumingException extends RuntimeException {
    public MessageConsumingException() {
    }

    public MessageConsumingException(final String message) {
        super(message);
    }

    public MessageConsumingException(final Exception cause) {
        super(cause);
    }

    public MessageConsumingException(final String message, final Exception cause) {
        super(message, cause);
    }
}
