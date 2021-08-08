package bowling.domain.turn;

import bowling.domain.score.TurnScore;
import bowling.domain.score.Score;

public abstract class Turn {
    protected final TurnScore score;

    protected Turn(final TurnScore score) {
        this.score = score;
    }

    protected boolean matchesScore(Score score) {
        return this.score.equals(score);
    }

    protected Score sumScore(Turn frame) {
        return this.score.sum(
                frame.score
        );
    }

    public final boolean isGutter() {
        return score.isZero();
    }
}
