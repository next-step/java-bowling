package bowling.domain;

public class FinalFrame extends Frame {

    private static final int MAX_SWING_COUNT = 3;
    private static final int NORMAL_FRAME_MAX_SWING_COUNT = 2;

    private int swingCount;
    private int sumOfScores;

    @Override
    public void swing(int score) {
        verifyScoreRange(score);
        sumOfScores += score;
        swingCount++;
        addHistory(score, sumOfScores);
    }

    @Override
    public boolean isEndedFrame() {
        return swingCount == MAX_SWING_COUNT ||
                secondSwingCheck();
    }

    private boolean secondSwingCheck() {

        if (swingCount < NORMAL_FRAME_MAX_SWING_COUNT) {
            return false;
        }

        if (doubleStrike()) {
            return false;
        }

        return !isSpare();
    }

    private boolean doubleStrike() {
        return sumOfScores == MAXIMUM_SCORE * 2;
    }

    private boolean isSpare() {
        return firstSwing() != MAXIMUM_SCORE && sumOfScores == MAXIMUM_SCORE;
    }
}
