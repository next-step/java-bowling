package qna.domain.exception;

public class UnAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(final String message, final Throwable cause, final boolean enableSuppression,
        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnAuthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(final String message) {
        super(message);
    }

    public UnAuthorizedException(final Throwable cause) {
        super(cause);
    }
}
