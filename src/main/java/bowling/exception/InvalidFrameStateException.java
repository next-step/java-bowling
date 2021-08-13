package bowling.exception;

public class InvalidFrameStateException extends RuntimeException {
    public InvalidFrameStateException(ErrorCode errorCode) {
        super(errorCode.message());
    }

    public InvalidFrameStateException(String message) {
        super(message);
    }

    public enum ErrorCode {
        NEXT_FRAME_FAILURE("다음 프레임을 가져올 수 없습니다."),
        BOWL_FAILURE("남은 투구 기회가 없습니다.");

        private final String message;

        ErrorCode(final String message) {
            this.message = message;
        }

        public String message() {
            return message;
        }
    }
}
