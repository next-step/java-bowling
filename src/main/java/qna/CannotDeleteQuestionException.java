package qna;

public class CannotDeleteQuestionException extends CannotDeleteException {

    private static final String CAN_NOT_DELETE_BY_DISMATCH_WRITER_ERROR_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    public CannotDeleteQuestionException(String message) {
        super(message);
    }

    public CannotDeleteQuestionException() {
        this(CAN_NOT_DELETE_BY_DISMATCH_WRITER_ERROR_MESSAGE);
    }

}
