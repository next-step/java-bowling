package domain.state;

import domain.Pins;

public class Strike implements State {

    private final static String STRIKE = " X ";
    private final Pins first;
    private final Pins second;

    public Strike(Pins downPins) {
        verify(downPins);
        this.first = downPins;
        this.second = Pins.EMPTY;
    }

    private void verify(Pins downPins) {
        if (!Pins.ALL.equals(downPins)) {
            throw new IllegalArgumentException("스트라이크 아님");
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
        return STRIKE;
    }
}
