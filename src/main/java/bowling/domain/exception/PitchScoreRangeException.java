package bowling.domain.exception;

public class PitchScoreRangeException extends IllegalArgumentException {
    private static final String INVALID_PITCH_SCORE_RANGE = "한 번 투구를 통해 얻을 수 있는 점수는 0부터 10까지입니다.";

    public PitchScoreRangeException() {
        super(INVALID_PITCH_SCORE_RANGE);
    }
}
