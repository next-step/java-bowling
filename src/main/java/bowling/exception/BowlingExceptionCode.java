package bowling.exception;

public enum BowlingExceptionCode {
    INVALID_PLAYER_NAME("사용자 이름은 null이거나 비어있으면 안됩니다."),
    INVALID_PLAYER_NAME_LENGTH("사용자 이름은 3자 이하여야 합니다."),
    INVALID_COUNT_OF_FALLEN_PINS("쓰러트린 핀이 남은 핀의 개수를 넘었습니다."),
    NO_SUCH_FRAME("index에 해당하는 Frame이 존재하지 않습니다."),
    NO_SUCH_SCORE_STRATEGY("frame에 맞는 점수 전략이 존재하지 않습니다."),
    FAIL_STRIKE_SCORE_COMPUTING("strike 점수 계산 중 에러가 발생했습니다.")
    ;

    private final String message;

    BowlingExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
