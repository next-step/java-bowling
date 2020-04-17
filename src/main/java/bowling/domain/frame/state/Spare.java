package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Spare implements State, Calculable {
    private final Pins second;

    public Spare(final Pins second) {
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
        return StateSymbol.SPARE.getSymbol();
    }

    @Override
    public int getKnockOverCount() {
        return second.count();
    }

    @Override
    public Score getScore() {
        return new Score(second.count(), 1);
    }
}
