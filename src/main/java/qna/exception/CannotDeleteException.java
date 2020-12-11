package qna.exception;

public class CannotDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }
}