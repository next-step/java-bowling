package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Strike implements State, Calculable {
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
    public Score getScore() {
        return new Score(10);
    }
}
