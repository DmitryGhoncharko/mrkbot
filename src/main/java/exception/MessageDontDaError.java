package exception;

public class MessageDontDaError extends Error{
    public MessageDontDaError() {
    }

    public MessageDontDaError(String message) {
        super(message);
    }

    public MessageDontDaError(String message, Throwable cause) {
        super(message, cause);
    }
}
