package domain.state;

import domain.Pins;

import static io.OutputResult.SYMBOL_DELIMITER;

public class Spares implements State {

    private final static String SPARES = "/";
    private final Pins first;
    private final Pins second;

    Spares(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (!Pins.ALL.equals(first.add(second))) {
            throw new IllegalArgumentException("핀이 남아 있는데");
        }
    }

    @Override
    public State bowl(Pins downPins) {
        throw new RuntimeException("더이상 진행 할 수 없습니다.");
    }

    @Override
    public Boolean isClosed() {
        return true;
    }

    @Override
    public String toSymbol() {
        return first + SYMBOL_DELIMITER + SPARES;
    }
}
