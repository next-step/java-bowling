package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Strike implements State, Calculable {
    private static final int STRIKE_COUNT = 10;

    @Override
    public State roll(final Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        return StateSymbol.STRIKE.getSymbol();
    }

    @Override
    public int getKnockOverCount() {
        return STRIKE_COUNT;
    }

    @Override
    public Score getScore() {
        return new Score(STRIKE_COUNT, 2);
    }
}
