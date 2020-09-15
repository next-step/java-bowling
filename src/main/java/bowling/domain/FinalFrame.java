package bowling.domain;

public class FinalFrame implements Frame {
    private static final String COLUMN = "|";
    private static final int FRAME_NO = 10;

    private ScoringHistory scoringHistory;
    private BowlingScore bonusRecord;
    private Frame parent;

    public static FinalFrame from(Frame parent) {
        return new FinalFrame(ScoringHistory.emptyHistory(), BowlingScore.emptyScore(), parent);
    }

    private FinalFrame(ScoringHistory scoringHistory, BowlingScore bonusRecord, Frame parent) {
        this.scoringHistory = scoringHistory;
        this.bonusRecord = bonusRecord;
        this.parent = parent;
    }

    @Override
    public String printableTitle() {
        return String.format(COLUMN_WITH_FORMAT, FRAME_NO, BLANK) + COLUMN;
    }

    @Override
    public String printableValue() {
        return String.format(COLUMN_WITH_FORMAT, scoringHistory.printableScoringHistoryStatus()
                + printableBonusScore(), BLANK) + COLUMN;
    }

    private String printableBonusScore() {
        if (scoringHistory.isStrikeOrSpare()) {
            return " | " + bonusRecord.printableScore();
        }

        return "";
    }

    @Override
    public boolean record(BowlingScore score) {
        if(scoringHistory.isEmpty()) {
            this.scoringHistory = ScoringHistory.firstTry(score);
            return isEnd();
        }

        if(scoringHistory.isDone()) {
            bonusRecord = score;
            return isEnd();
        }

        this.scoringHistory = ScoringHistory.secondTry(scoringHistory, score);
        return isEnd();
    }

    @Override
    public Frame makeNextFrame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return (scoringHistory.isStrikeOrSpare() && !bonusRecord.isEmpty()) || (scoringHistory.isDone());
    }
}
