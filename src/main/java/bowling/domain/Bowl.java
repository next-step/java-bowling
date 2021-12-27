package bowling.domain;

import static bowling.domain.Pin.MAX_PIN_COUNT;

public class Bowl {
    protected final Pins pins;
    protected final States states;

    public Bowl(Pins pins, States states) {
        this.pins = pins;
        this.states = states;
    }

    public static Bowl of(Pins pins, States states) {
        return new Bowl(
                pins,
                states
        );
    }

    public static Bowl first() {
        return new Bowl(
                Pins.init(),
                States.init()
        );
    }

    protected Pin getLastPin() {
        if(pins.isEmpty()) {
            return Pin.first();
        }
        return pins.getLast();
    }

    public Bowl bowl(Pin pin) {
        State last = getLastState();
        State next = last.bowl(getLastPin(), pin);
        if( last == State.READY) {
            states.removeLast();
        }
        states.add(next);
        pins.add(pin);
        if(pinsTotal() > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("1~9 프레임에서 쓰러트릴 수 있는 볼링핀의 총 갯수는 10을 초과할 수 없습니다.");
        }
        return this;
    }

    public State getLastState() {
        return states.getLast();
    }


    public String symbol() {
        return states.symbol(pins);
    }

    public int pinsTotal() {
        return pins.total();
    }
}
