package qna;

public class CannotDeleteAnswersException extends CannotDeleteException {
    private static final String CANNOT_DELETE_ANSWERS_EXCEPTION_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    public CannotDeleteAnswersException() {
        super(CANNOT_DELETE_ANSWERS_EXCEPTION_MESSAGE);
    }

}
