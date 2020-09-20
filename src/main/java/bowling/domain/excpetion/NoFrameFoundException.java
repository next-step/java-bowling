package bowling.domain.excpetion;

public class NoFrameFoundException extends RuntimeException {

    public NoFrameFoundException() {
        super();
    }

    public NoFrameFoundException(String message) {
        super(message);
    }

    public NoFrameFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFrameFoundException(Throwable cause) {
        super(cause);
    }

    protected NoFrameFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
