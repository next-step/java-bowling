package bowling.domain;

public class LastFrame extends Frame {

    private static final int MAX_SWING_COUNT = 3;
    private static final int NORMAL_FRAME_MAX_SWING_COUNT = 2;

    private int swingCount;
    private final Score score;

    public LastFrame() {
        this.swingCount = 0;
        this.score = new Score(false);
    }

    @Override
    public void swing(int score) {
        swingCount++;
        this.score.swing(score, swingCount);
        addHistory(score);
    }

    @Override
    public boolean isEndedFrame() {
        return swingCount == MAX_SWING_COUNT || secondSwingCheck();
    }

    @Override
    public int getScore() {
        return score.getValue();
    }

    private boolean secondSwingCheck() {

        if (swingCount < NORMAL_FRAME_MAX_SWING_COUNT) {
            return false;
        }

        if (doubleStrike()) {
            return false;
        }

        return isNotSpare();
    }

    private boolean doubleStrike() {
        return score.isDoubleStrike(swingCount);
    }

    private boolean isNotSpare() {
        return !(firstSwing() != MAXIMUM_SCORE && score.isSpare(swingCount));
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isNotSwing() {
        return swingCount == 0;
    }
}
