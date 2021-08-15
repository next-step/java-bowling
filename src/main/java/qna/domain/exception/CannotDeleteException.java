package qna.domain.exception;

public class CannotDeleteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CannotDeleteException(final String message) {
        super(message);
    }
}
