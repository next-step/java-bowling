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

    public Pin get(int i) {
        return pins.get(i);
    }

    public int getScore() {
        return pins.stream()
                .mapToInt(Pin::getPin)
                .sum();
    }

    public void removeRecent() {
        pins.remove(pins.size() - 1);
    }
}