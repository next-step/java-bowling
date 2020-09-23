package bowling.domain;

public abstract class Frame {

    public static final int MINIMAL_SCORE = 0;
    public static final int MAXIMUM_SCORE = 10;

    protected static final String ERR_SCORE_RANGE = "0부터 10까지의 값만 입력할 수 있습니다.";
    protected static final String ERR_SUM_OF_SCORE_VALUE = "한 프레임 당 투구 점수는 최대 10점입니다.";

    protected final SwingHistory swingHistory;

    public Frame() {
        swingHistory = new SwingHistory();
    }

    abstract void swing(int score);

    abstract boolean isEndedFrame();

    protected void addHistory(int score, int sumOfScores) {
        swingHistory.addHistory(score, sumOfScores);
    }

    public String swingHistoryToString() {
        return swingHistory.toString();
    }

    protected void verifyScoreRange(int score) {
        if (score < MINIMAL_SCORE || score > MAXIMUM_SCORE) {
            throw new IllegalArgumentException(ERR_SCORE_RANGE);
        }
    }
}
