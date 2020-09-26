package bowling.domain;

public class NormalFrame extends Frame {

    private static final int MAX_SWING_COUNT = 2;

    private final Score score;
    private int swingCount;

    public NormalFrame() {
        this.score = new Score();
        this.swingCount = 0;
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
    public String getScore() {
        return ""; // TODO 로직 작성
    }
}
