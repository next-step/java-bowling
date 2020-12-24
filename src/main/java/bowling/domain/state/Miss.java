package bowling.domain.state;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.Symbol;

public class Miss implements State {
    public static final String DELIMITER = "|";
    public static final int PINS_ZERO = 0;

    private final Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public Miss(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public Score getScore() {
        Score score = new Score(firstPins);
        score.setSecond(secondPins);
        return score;
    }

    @Override
    public boolean isFinished() {
        return this.secondPins != null;
    }

    @Override
    public String getSymbol() {
        String symbol = String.valueOf(this.firstPins.get());

        if (isFirstGutter()) {
            symbol = Symbol.GUTTER.getSymbol();
        }
        if (this.secondPins != null) {
            symbol = symbol + DELIMITER
                    + (isSecondGutter() ? Symbol.GUTTER.getSymbol() : this.secondPins.get());
        }
        return symbol;
    }

    private boolean isSecondGutter() {
        return this.secondPins.get() == PINS_ZERO;
    }

    private boolean isFirstGutter() {
        return this.firstPins.get() == PINS_ZERO;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
