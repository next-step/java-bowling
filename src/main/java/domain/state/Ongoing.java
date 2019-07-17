package domain.state;

import domain.Pins;

import static domain.Pins.ALL;
import static io.OutputResult.SYMBOL_DELIMITER;

public class Ongoing implements State {

    private Pins first;

    Ongoing(Pins first) {
        this.first = first;
    }

    @Override
    public State bowl(Pins downPins) {
        Pins downWholePins = first.add(downPins);
        if (ALL.equals(downWholePins)) {
            return new Spares(first, downPins);
        }
        return new Open(first, downPins);
    }

    @Override
    public Boolean isClosed() {
        return false;
    }

    @Override
    public String toSymbol() {
        return first + SYMBOL_DELIMITER;
    }
}
