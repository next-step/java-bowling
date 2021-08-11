package bowling.exception;

public class PlayerEmptyException extends RuntimeException {
    public PlayerEmptyException(ErrorCode errorCode) {
        super(errorCode.message());
    }

    public enum ErrorCode {
        EMPTY("게임을 진행할 플레이어가 없습니다."),
        NOT_FOUND("존재하지 않는 플레이어 입니다.");

        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String message() {
            return message;
        }
    }
}
