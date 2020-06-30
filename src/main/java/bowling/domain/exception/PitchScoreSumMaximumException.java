package bowling.domain.exception;

public class PitchScoreSumMaximumException extends IllegalArgumentException {
    private static final String OVER_SCORE = "제공되는 볼링핀의 개수보다 많은 수를 넘어뜨릴 수 없습니다.";

    public PitchScoreSumMaximumException() {
        super(OVER_SCORE);
    }
}
