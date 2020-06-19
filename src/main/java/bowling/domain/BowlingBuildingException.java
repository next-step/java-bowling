package bowling.domain;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "참가 선수 이름은 3글자만 허용됩니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}
