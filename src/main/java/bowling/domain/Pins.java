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
}
