package bowling.exception;

public enum ErrorMessage {
    NUMBER_OF_PINS_OUT_OF_RANGE("볼링핀의 개수는 0 ~ 10개 사이 입니다."),
    WRONG_NAME_FORMAT("잘못된 이름 입니다."),
    SCORE_OUT_OF_RANGE("스코어는 0보다 작을 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
