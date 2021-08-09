package qna.exception;

public class CannotDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;

    public CannotDeleteException(ErrorCode errorCode) {
        super(errorCode.message());

        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        NOT_PERMISSIONS("질문을 삭제할 권한이 없습니다."),
        EXISTS_ANSWER("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String message() {
            return message;
        }
    }
}