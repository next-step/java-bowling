package bowling.domain;

public class FinalFrame implements Frame {
    private static final String COLUMN = "|";
    private static final int FRAME_NO = 10;

    private BonusScoringHistory bonusScoringHistory;
    private Frame parent;

    public static FinalFrame from(Frame parent) {
        return new FinalFrame(BonusScoringHistory.emptyBonusHistory(), parent);
    }

    private FinalFrame(BonusScoringHistory bonusScoringHistory, Frame parent) {
        this.bonusScoringHistory = bonusScoringHistory;
        this.parent = parent;
    }

    @Override
    public String printableTitle() {
        return String.format(COLUMN_WITH_FORMAT, BLANK, FRAME_NO, BLANK) + COLUMN;
    }

    @Override
    public String printableValue() {
        return String.format(COLUMN_WITH_FORMAT, BLANK, bonusScoringHistory.printableBonusHistoryStatus(), BLANK) + COLUMN;
    }

    @Override
    public boolean record(BowlingScore score) {
        if(bonusScoringHistory.isFirstTry()) {
            this.bonusScoringHistory = BonusScoringHistory.firstTry(score);
            return isEnd();
        }

        if(bonusScoringHistory.isSecondTry()) {
            this.bonusScoringHistory = BonusScoringHistory.secondTry(bonusScoringHistory, score);
            return isEnd();
        }

        this.bonusScoringHistory = BonusScoringHistory.thirdTry(bonusScoringHistory, score);
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
        return bonusScoringHistory.isDone();
    }
}
