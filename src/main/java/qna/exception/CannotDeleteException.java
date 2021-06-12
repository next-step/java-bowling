package qna.exception;

public class CannotDeleteException extends RuntimeException {

    public static final String QUESTION_DELETE_PERMISSION = "질문을 삭제할 권한이 없습니다.";
    public static final String QUESTION_WITH_OTHERS_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private static final long serialVersionUID = 1L;

    public CannotDeleteException(String message) {
        super(message);
    }
}
