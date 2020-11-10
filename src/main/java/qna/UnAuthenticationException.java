package qna;

public class UnAuthenticationException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(Throwable cause) {
        super(cause);
    }
}
