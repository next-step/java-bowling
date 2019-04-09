package domain.pin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return pins.stream()
                    .map(Pin::toString)
                    .collect(Collectors.joining(" "));
    }
}