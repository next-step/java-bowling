package qna.domain.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
    }

    public ForbiddenException(final String message) {
        super(message);
    }
}
