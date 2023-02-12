package exception;

public class StikerWasSendTodayError extends Error{
    public StikerWasSendTodayError() {
    }

    public StikerWasSendTodayError(String message) {
        super(message);
    }

    public StikerWasSendTodayError(String message, Throwable cause) {
        super(message, cause);
    }
}
