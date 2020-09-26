package bowling.domain;

public abstract class Frame {

    public static final int MINIMAL_SCORE = 0;
    public static final int MAXIMUM_SCORE = 10;

    private final SwingHistory swingHistory;

    public Frame() {
        swingHistory = new SwingHistory();
    }

    abstract void swing(int score);

    abstract boolean isEndedFrame();

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
