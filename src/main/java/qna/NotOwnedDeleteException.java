package qna;

public class NotOwnedDeleteException extends CannotDeleteException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    public NotOwnedDeleteException() {
        super(ERROR_MESSAGE);
    }
}