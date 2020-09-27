package bowling.domain;

public class Score {

    public static final int MINIMAL_SWING_SCORE = 0;
    public static final int MAXIMUM_SWING_SCORE = 10;
    public static final int DOUBLE_STRIKE_SCORE = MAXIMUM_SWING_SCORE * 2;

    private static final int FIRST_SWING = 1;
    private static final int SECOND_SWING = 2;

    private static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";
    private static final String ERR_SUM_OF_SCORE_VALUE = "한 프레임 당 투구 점수는 최대 10점입니다.";

    private int score;
    private final boolean isNormalFrame;

    public Score() {
        this(true);
    }

    public Score(boolean isNormalFrame) {
        this.score = 0;
        this.isNormalFrame = isNormalFrame;
    }

    public void swing(int score, int swingCount) {
        verifyScoreRange(score);
        this.score += score;

        if (isNormalFrame) {
            verifySpareScore(swingCount);
        }
    }

    private void verifyScoreRange(int score) {
        if (score < MINIMAL_SWING_SCORE || score > MAXIMUM_SWING_SCORE) {
            throw new IllegalArgumentException(ERR_SCORE_RANGE);
        }
    }

    private void verifySpareScore(int swingCount) {
        if (swingCount == SECOND_SWING && score > MAXIMUM_SWING_SCORE) {
            throw new IllegalArgumentException(ERR_SUM_OF_SCORE_VALUE);
        }
    }

    public boolean isFirstSwingStrike(int swingCount) {
        return swingCount == FIRST_SWING && score == MAXIMUM_SWING_SCORE;
    }

    public boolean isDoubleStrike(int swingCount) {
        return swingCount == SECOND_SWING && score == DOUBLE_STRIKE_SCORE;
    }

    public boolean isSpare(int swingCount) {
        return swingCount == SECOND_SWING && score == MAXIMUM_SWING_SCORE;
    }

    public int getValue() {
        return score;
    }
}
