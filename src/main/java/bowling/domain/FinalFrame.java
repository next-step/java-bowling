package bowling.domain;

import bowling.constant.GameState;
import bowling.ui.BowlingResultView;

public class FinalFrame implements Frame {
    private static final String COLUMN = "|";
    private static final int FRAME_NO = 10;

    private ScoringHistory scoringHistory;
    private BowlingScore bonusRecord;
    private Frame parent;

    public static FinalFrame from(Frame parent) {
        return new FinalFrame(ScoringHistory.emptyHistory(), BowlingScore.zeroScore(), parent);
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
        if (scoringHistory.isEmptyStatus()) {
            return "";
        }

        return BowlingResultView.SCORE_DELIMITER + bonusRecord.printableScore();
    }

    @Override
    public GameState record(BowlingScore score) {
        if(scoringHistory.isEmptyStatus()) {
            this.scoringHistory = ScoringHistory.firstTry(score);
            return scoringHistory.getGameState();
        }

        if(scoringHistory.isPlayingStatus()) {
            this.scoringHistory = ScoringHistory.secondTry(scoringHistory, score);
        }

        if(scoringHistory.isSpareOrStrike()) {
            return GameState.PLAYING;
        }

        return GameState.FINISH_CURRENT_FRAME;
    }

    @Override
    public Frame makeNextFrame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }
}
