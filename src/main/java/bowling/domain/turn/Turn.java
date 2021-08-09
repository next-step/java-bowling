package bowling.domain.turn;

import bowling.domain.score.TurnScore;
public class Turn {
    protected final TurnScore score;

    public Turn(final TurnScore score) {
        this.score = score;
    }

    public final TurnScore score() {
        return score;
    }

    public final boolean isGutter() {
        return score.isZero();
    }
}
