package bowling.domain;

public class Score {

    public static final int MINIMAL_SWING_SCORE = 0;
    public static final int MAXIMUM_SWING_SCORE = 10;

    protected static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";
    protected static final String ERR_SUM_OF_SCORE_VALUE = "한 프레임 당 투구 점수는 최대 10점입니다.";

    private int score;
    private final boolean isFinalFrame;

    public Score(boolean isFinalFrame) {
        this(0, isFinalFrame);
    }

    public Score(int score, boolean isFinalFrame) {
        this.score = 0;
        this.isFinalFrame = isFinalFrame;
    }

    public int getValue() {
        return score;
    }
}
