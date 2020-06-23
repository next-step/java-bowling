package bowling.exception;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "이름은 3글자의 문자열이어야 합니다.";
    public static final String INVALID_SCORE_RANGE = "한 번 투구를 통해 얻을 수 있는 점수는 0부터 10까지입니다.";
    public static final String INVALID_NORMAL_PITCHES = "1~9번 프레임은 스트라이크나 스페어 이후 추가 투구가 불가능합니다.";
    public static final String OVER_SCORE = "1~9번 프레임은 두 투구의 합이 10점을 초과할 수 없습니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}
