package bowling.domain.framescore;

import bowling.domain.score.TurnScores;

public class FinalFrameScore extends FrameScore {
    private final boolean completed;
    private final FrameScore baseFrameScore;

    public FinalFrameScore(final FrameScore baseFrameScore, final TurnScores turnScores, boolean completed) {
        super(turnScores);
        this.baseFrameScore = baseFrameScore;
        this.completed = completed;
    }

    public boolean isSpare() {
        return baseFrameScore instanceof Spare;
    }

    @Override
    public boolean isShowScoreValue() {
        return completed;
    }

    @Override
    public boolean isCompleted() {
        return isShowScoreValue();
    }
}
