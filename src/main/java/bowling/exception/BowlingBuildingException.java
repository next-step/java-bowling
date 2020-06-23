package bowling.exception;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "이름은 3글자의 문자열이어야 합니다.";
    public static final String INVALID_SCORE_RANGE = "한 번 투구를 통해 얻을 수 있는 점수는 0부터 10까지입니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}
