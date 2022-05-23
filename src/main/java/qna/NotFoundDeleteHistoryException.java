package qna;

public class NotFoundDeleteHistoryException extends RuntimeException {

    private static final String MESSAGE = "삭제되지 않은 항목이 있어 삭제 히스토리를 찾을 수 없습니다.";

    public NotFoundDeleteHistoryException() {
        super(MESSAGE);
    }
}
