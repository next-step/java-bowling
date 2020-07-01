package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private final List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public void add(Pin pin) {
        this.pins.add(pin);
    }

    public List<Pin> getPins() {
        return pins;
    }

    public Pin getLastPin() {
        int size = this.pins.size();
        if (size == 0) {
            return new Pin(Pin.MIN_PIN);
        }
        return this.pins.get(size - 1);
    }

    public Pin getBeforePin() {
        int size = this.pins.size();
        if (size == 0 || size == 1) {
            return new Pin(Pin.MIN_PIN);
        }

        return getLastPreviousPin(size);
    }

    private Pin getLastPreviousPin(int size) {
        return this.pins.get(size - 2);
    }
}
