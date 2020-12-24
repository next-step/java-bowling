package bowling.domain.state;

import bowling.domain.Symbol;
import bowling.domain.score.Pins;
import bowling.domain.score.Score;

public class Gutter implements State {
    public static final String DELIMITER = "|";

    private final Pins firstPins; // finish 체크용
    private Pins secondPins;

    public Gutter(Pins firstPins) {
        validate(firstPins);
        this.firstPins = firstPins;
    }

    public Gutter(Pins firstPins, Pins secondPins) {
        validate(firstPins);
        validate(secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validate(Pins secondPins) {
        if (secondPins.get() != Symbol.GUTTER.getPins().get()) {
            throw new IllegalArgumentException("잘못된 핀입니다.");
        }
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
        String symbol = Symbol.GUTTER.getSymbol();
        if (this.secondPins != null) {
            symbol += DELIMITER + Symbol.GUTTER.getSymbol();
        }
        return symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
