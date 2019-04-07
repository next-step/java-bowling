package domain.pin;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private final List<Pin> pins = new ArrayList<>();

    public void add(Pin pin) {
        pins.add(pin);
    }

    public int size() {
        return pins.size();
    }
}