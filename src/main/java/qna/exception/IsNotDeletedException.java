package qna.exception;

public class IsNotDeletedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IsNotDeletedException(String message) {
        super(message);
    }
}
