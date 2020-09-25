package bowling.domain.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pins {
    private final List<Pin> pins;

    public Pins() {
        this(new ArrayList<>());
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public void add(int pin) {
        pins.add(Pin.of(pin));
    }

    public int size() {
        return pins.size();
    }

    public List<Pin> getPins() {
        return Collections.unmodifiableList(pins);
    }
}
