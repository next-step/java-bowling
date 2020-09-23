package bowling.domain;

public class NormalFrame extends Frame {

    private static final int MAX_SWING_COUNT = 2;

    private int swingCount;
    private int sumOfScores;

    public NormalFrame() {
        this.swingCount = 0;
        this.sumOfScores = 0;
    }

    @Override
    public void swing(int score) {
        verifyScoreRange(score);
        sumOfScores += score;
        swingCount++;
        addHistory(score);
        verifySumOfScore();
    }

    @Override
    boolean isEndedFrame() {
        return swingCount >= MAX_SWING_COUNT || sumOfScores == MAXIMUM_SCORE;
    }

    private void verifyScoreRange(int score) {
        if (score < MINIMAL_SCORE || score > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SCORE_RANGE);
        }
    }

    private void verifySumOfScore() {
        if (sumOfScores > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SUM_OF_SCORE_VALUE);
        }
    }
}
