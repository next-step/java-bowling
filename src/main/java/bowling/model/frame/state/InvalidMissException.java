package bowling.model.frame.state;

class InvalidMissException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "핀을 모두 쓰러트릴 수 없습니다";

    InvalidMissException() {
        super(ERROR_MESSAGE);
    }
}