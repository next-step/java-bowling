package bowling.domain.framescore;

import bowling.domain.score.TurnScores;

public class Normal extends FrameScore {
    private final boolean completed;

    protected Normal(final TurnScores turnScores, final boolean isCompleted) {
        super(turnScores);
        this.completed = isCompleted;
    }

    public static Normal of(TurnScores turnScores, boolean completed) {
        return new Normal(turnScores, completed);
    }

    @Override
    public boolean isShowScoreValue() {
        return completed;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }
}
