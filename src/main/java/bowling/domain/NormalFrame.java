package bowling.domain;

public class NormalFrame extends Frame {

    private int sumOfScores;

    public NormalFrame() {
        this.sumOfScores = 0;
    }

    @Override
    public void swing(int score) {
        verifyScoreRange(score);
        sumOfScores += score;
        addHistory(score);
        verifySumOfScore();
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
