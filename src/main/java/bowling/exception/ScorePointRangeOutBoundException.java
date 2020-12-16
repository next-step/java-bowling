package bowling.exception;

public class ScorePointRangeOutBoundException extends IllegalArgumentException {

    private static final String SCORE_POINT_RANGE_OUT_BOUND = "포인트는 0또는 10사이여야 합니다.";

    public ScorePointRangeOutBoundException() {
        super(SCORE_POINT_RANGE_OUT_BOUND);
    }
}
