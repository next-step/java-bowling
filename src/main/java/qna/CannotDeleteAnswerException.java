package qna;

public class CannotDeleteAnswerException extends CannotDeleteException {

    private static final String CAN_NOT_DELETE_BY_DISMATCH_WRITER_ERROR_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    public CannotDeleteAnswerException(String message) {
        super(message);
    }

    public CannotDeleteAnswerException() {
        this(CAN_NOT_DELETE_BY_DISMATCH_WRITER_ERROR_MESSAGE);
    }

}
