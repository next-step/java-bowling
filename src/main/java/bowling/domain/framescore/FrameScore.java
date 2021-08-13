package bowling.domain.framescore;

import bowling.domain.score.TurnScores;

public abstract class FrameScore {
    private final TurnScores turnScores;

    public FrameScore(TurnScores turnScores) {
        this.turnScores = turnScores;
    }

    public abstract boolean isShowScoreValue();

    public abstract boolean isCompleted();

    public TurnScores turnScores() {
        return turnScores;
    }
}
