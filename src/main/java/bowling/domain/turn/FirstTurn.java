package bowling.domain.turn;

import bowling.domain.score.TurnScore;

public final class FirstTurn extends Turn {
    private static final TurnScore STRIKE_VALUE = TurnScore.max();

    public FirstTurn(final TurnScore score) {
        super(score);
    }

    public boolean isStrike() {
        return STRIKE_VALUE.equals(score);
    }

    public SecondTurn secondTurn(TurnScore score) {
        return new SecondTurn(this, score);
    }
}
