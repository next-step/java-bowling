package qna.exception;

public class CannotDeleteException extends RuntimeException {

    public static final String NO_DELETE_PERMISSION = "질문을 삭제할 권한이 없습니다.";

    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }
}
