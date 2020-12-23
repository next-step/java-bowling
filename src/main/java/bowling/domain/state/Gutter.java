package bowling.domain.state;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.Symbol;

public class Gutter implements State {
    public static final String DELIMITER = "|";

    private final Pins firstPins;
    private Pins secondPins;

    public Gutter(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public Gutter(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public boolean isFinished() {
        return secondPins != null;
    }

    @Override
    public String getSymbol() {
        String ret = Symbol.GUTTER.getSymbol();
        if (this.secondPins != null) {
            ret = ret + DELIMITER + Symbol.GUTTER.getSymbol();
        }
        return ret;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
