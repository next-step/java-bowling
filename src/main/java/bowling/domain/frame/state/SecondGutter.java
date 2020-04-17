package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class SecondGutter implements State, Calculable {
    @Override
    public State roll(final Pins second) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        return StateSymbol.GUTTER.getSymbol();
    }

    @Override
    public int getKnockOverCount() {
        return Pins.ZERO;
    }

    @Override
    public Score getScore() {
        return new Score(Score.ZERO, Score.ZERO);
    }
}
