package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private final List<Pin> pins;

    private Pins() {
        pins = new ArrayList<>();
    }

    public static Pins init() {
        return new Pins();
    }

    public void add(Pin pin) {
        pins.add(pin);
    }

    private int getLastIndex() {
        return pins.size() - 1;
    }

    public Pin getLast() {
        return pins.get(getLastIndex());
    }

    public int total() {
        return pins.stream().map(Pin::getFallenPinCount).reduce(Integer::sum).orElse(0);
    }

    public Pin get(int index) {
        if (index > getLastIndex()) {
            return getLast();
        }
        return pins.get(index);
    }

    public boolean isEmpty() {
        return pins.isEmpty();
    }

    public int size() {
        return pins.size();
    }
}
