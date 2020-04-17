package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Miss implements State, Calculable {
    private final Pins second;

    Miss(final Pins second) {
        this.second = second;
    }

    @Override
    public State roll(final Pins knockOverPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        if (second.isGutter()) {
            return StateSymbol.GUTTER.getSymbol();
        }
        return String.valueOf(second.count());
    }

    @Override
    public int getKnockOverCount() {
        return second.count();
    }

    @Override
    public Score getScore() {
        return new Score(second.count(), Score.ZERO);
    }
}
