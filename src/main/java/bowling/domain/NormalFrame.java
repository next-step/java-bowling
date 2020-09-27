package bowling.domain;

public class NormalFrame extends Frame {

    private static final int MAX_SWING_COUNT = 2;

    private int swingCount;
    private final Score score;

    public NormalFrame() {
        this.swingCount = 0;
        this.score = new Score();
    }

    @Override
    public void swing(int score) {
        swingCount++;
        this.score.swing(score, swingCount);
        addHistory(score);
    }

    @Override
    public boolean isEndedFrame() {
        return swingCount >= MAX_SWING_COUNT || score.isFirstSwingStrike(swingCount);
    }

    @Override
    public int getScore() {
        return score.getValue();
    }

    @Override
    public boolean isSpare() {
        return score.isSpare(swingCount);
    }

    @Override
    public boolean isStrike() {
        return score.isFirstSwingStrike(swingCount);
    }

    @Override
    public boolean isNotSwing() {
        return swingCount == 0;
    }
}
