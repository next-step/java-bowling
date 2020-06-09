package qna;

public class NotAuthorizedDeleteException extends CannotDeleteException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    public NotAuthorizedDeleteException() {
        super(ERROR_MESSAGE);
    }
}