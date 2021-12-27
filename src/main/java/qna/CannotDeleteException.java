package qna;

public class CannotDeleteException extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }

    public CannotDeleteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
