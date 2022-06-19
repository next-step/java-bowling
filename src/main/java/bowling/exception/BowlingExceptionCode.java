package bowling.exception;

public enum BowlingExceptionCode {
    INVALID_PLAYER_NAME("사용자 이름은 null이거나 비어있으면 안됩니다."),
    INVALID_FRAME_INDEX("찾으려는 index의 frame이 없습니다."),
    INVALID_PLAYER_NAME_LENGTH("사용자 이름은 3자 이하여야 합니다."),
    INVALID_COUNT_OF_FALLEN_PINS("쓰러트린 핀이 남은 핀의 개수를 넘었습니다.");

    private final String message;

    BowlingExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
