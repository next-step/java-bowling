package bowling.domain;

public class Swing {

    public static final int MINIMAL_SCORE = 0;
    public static final int MAXIMUM_SCORE = 10;
    public static final int DUMMY_SCORE = -10;

    private static final int FIRST_SWING = 1;
    private static final int SECOND_SWING = 2;
    private static final int THIRD_SWING = 3;

    private static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";

    private int swingCount;
    private int score;

    public Swing() {
        swingCount = score = 0;
    }

    public void addScore(int score) {
        verifyScoreRange(score);
        this.swingCount++;
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    private void verifyScoreRange(int score) {
        if (score < MINIMAL_SCORE || score > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SCORE_RANGE);
        }
    }

    public boolean isSpare() {
        return swingCount == SECOND_SWING && score == MAXIMUM_SCORE;
    }

    public boolean isStrike() {
        return swingCount == FIRST_SWING && score == MAXIMUM_SCORE;
    }

    public boolean isNormalFrameEnd() {
        return swingCount >= SECOND_SWING;
    }

    public boolean isLastFrameEnd() {
        return (swingCount == SECOND_SWING && score < MAXIMUM_SCORE) || swingCount >= THIRD_SWING;
    }

    public void updateScore(int score) {
        this.score += score;
    }
}
