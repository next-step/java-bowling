package domain.state;

import domain.Pins;

public class Spares implements State {

    private final Pins first;
    private final Pins second;

    Spares(Pins first, Pins second) {
        verify(first, second);
        this.first = first;
        this.second = second;
    }

    private void verify(Pins first, Pins second) {
        if (!Pins.EMPTY.equals(first.minus(second))) {
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
}
