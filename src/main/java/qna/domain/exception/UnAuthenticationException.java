package qna.domain.exception;

public class UnAuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(final String message, final Throwable cause, final boolean enableSuppression,
        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(final String message) {
        super(message);
    }

    public UnAuthenticationException(final Throwable cause) {
        super(cause);
    }
}
