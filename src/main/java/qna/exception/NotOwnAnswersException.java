package qna.exception;

public class NotOwnAnswersException extends CannotDeleteException {

    public static final String NOT_OWN_ANSWERS = "질문의 작성자가 아닌 유저가 답변을 달았을경우 삭제가 불가능합니다.";

    public NotOwnAnswersException() {
        super(NOT_OWN_ANSWERS);
    }
}
