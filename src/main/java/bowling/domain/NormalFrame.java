package bowling.domain;

import bowling.constant.GameState;

public class NormalFrame implements Frame {
    private static final String LAST_FRAME_NO = "09";

    private String frameNo;
    private ScoringHistory scoringHistory;
    private Frame parent;

    public static NormalFrame of(String frameNo, Frame parent) {
        return new NormalFrame(frameNo, ScoringHistory.emptyHistory(), parent);
    }

    private NormalFrame(String frameNo, ScoringHistory scoringHistory, Frame parent) {
        this.frameNo = frameNo;
        this.scoringHistory = scoringHistory;
        this.parent = parent;
    }

    @Override
    public String printableTitle() {
        return String.format(COLUMN_WITH_FORMAT, frameNo, BLANK);
    }

    @Override
    public String printableValue() {
        return String.format(COLUMN_WITH_FORMAT, scoringHistory.printableScoringHistoryStatus(), BLANK);
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

        return scoringHistory.getGameState();
    }

    @Override
    public Frame makeNextFrame() {
        if(isLast()) {
            return FinalFrame.from(this);
        }
        return NormalFrame.of(getNextFrameNo(), this);
    }

    private boolean isLast() {
        return frameNo.equals(LAST_FRAME_NO);
    }

    private String getNextFrameNo() {
        return "0"+(Integer.parseInt(frameNo)+1);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }


}
