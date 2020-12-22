package bowling.domain.state;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.Symbol;

public class Strike implements State {
    public static final int PINS_STRIKE = 10;

    @Override
    public Score getScore() {
        return new Score(new Pins(PINS_STRIKE));
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String getSymbol() {
        return Symbol.STRIKE.getSymbol();
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
