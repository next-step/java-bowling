package bowling.domain.framescore;

import bowling.domain.score.TurnScores;

public class FinalFrameScore extends Normal {
    private final FrameScore baseFrameScore;

    public FinalFrameScore(final FrameScore baseFrameScore, final TurnScores turnScores, boolean showScoreValue) {
        super(turnScores, showScoreValue);
        this.baseFrameScore = baseFrameScore;
    }

    public boolean isSpare() {
        return baseFrameScore instanceof Spare;
    }

    @Override
    public boolean isCompleted() {
        return isShowScoreValue();
    }
}
