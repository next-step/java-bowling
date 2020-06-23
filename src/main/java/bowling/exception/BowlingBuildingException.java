package bowling.exception;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "이름은 3글자의 문자열이어야 합니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}
