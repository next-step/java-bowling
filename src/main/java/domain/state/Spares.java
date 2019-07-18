package domain.state;

import domain.Pins;

import static io.OutputResult.SYMBOL_DELIMITER;

public class Spares implements State {

    private final static String SPARES = "/";
    private final Pins first;
    private final Pins second;

    public Spares(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (isNotSpares(first, second)) {
            throw new IllegalArgumentException("핀이 남아 있는데");
        }
    }

    private boolean isNotSpares(Pins first, Pins second) {
        return !Pins.ALL.equals(first.add(second));
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public String toSymbol() {
        return first + SYMBOL_DELIMITER + SPARES;
    }
}
