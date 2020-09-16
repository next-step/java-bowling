package qna;

public class NotAuthorizedDeleteException extends CannotDeleteException {
    private static final String NOT_AUTHORIZED_DELETE_EXCEPTION_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    public NotAuthorizedDeleteException() {
        super(NOT_AUTHORIZED_DELETE_EXCEPTION_MESSAGE);
    }

}
