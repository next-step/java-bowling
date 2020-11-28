package bowling.domain.score;

public class InvalidScoreAddException extends RuntimeException {
    private static final String MESSAGE = "한프레임 내의 시도 횟수는 프레임별 최대 횟수를 넘을 수 없습니다.";

    public InvalidScoreAddException() {
        super(MESSAGE);
    }
}
