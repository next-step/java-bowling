package qna;

public class CannotDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private static final String CANNOT_DELETE_ERROR_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    public CannotDeleteException(String message) {
        super(message);
    }

    public CannotDeleteException() {
        this(CANNOT_DELETE_ERROR_MESSAGE);
    }

}