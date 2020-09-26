package bowling.domain;

public abstract class Frame {

    public static final int MINIMAL_SCORE = 0;
    public static final int MAXIMUM_SCORE = 10;

    private final SwingHistory swingHistory;

    public Frame() {
        swingHistory = new SwingHistory();
    }

    public abstract void swing(int score);

    public abstract boolean isEndedFrame();

    public abstract String getScore();

    protected void addHistory(int score) {
        swingHistory.addHistory(score);
    }

    public String swingHistoryToString() {
        return swingHistory.toString();
    }

    protected int firstSwing() {
        return swingHistory.firstSwing();
    }
}
