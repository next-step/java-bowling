package qna.exception;

public class NotOwnedChildContentException extends RuntimeException{

    private static final String MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    public NotOwnedChildContentException() {
        super(MESSAGE);
    }
}
