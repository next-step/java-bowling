package qna;

public class CannotDeleteException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }
}
