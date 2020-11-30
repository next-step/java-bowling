package bowling.domain.score;

public class InvalidMaxScoresException extends RuntimeException {
    private static final String MESSAGE = "한프레임 내의 점수는 프레임별 최대 점수를 넘을 수 없습니다.";

    public InvalidMaxScoresException() {
        super(MESSAGE);
    }
}
