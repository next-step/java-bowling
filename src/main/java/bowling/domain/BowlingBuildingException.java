package bowling.domain;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PIN_INDEX = "유효한 볼링 핀 인덱스 번호는 0 - 9 범위입니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}
