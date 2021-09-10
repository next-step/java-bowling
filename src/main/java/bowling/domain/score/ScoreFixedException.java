package bowling.domain.score;

public class ScoreFixedException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "이미 점수 계산이 완료되었습니다. 보너스 점수를 추가할 수 없습니다.";

    public ScoreFixedException() {
        super(DEFAULT_MESSAGE);
    }
}
