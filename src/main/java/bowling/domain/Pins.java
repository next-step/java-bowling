package bowling.domain;

import java.util.LinkedList;

public class Pins {
    private static final int MIN_SIZE = 2;

    private final LinkedList<Pin> pins;

    private Pins() {
        pins = new LinkedList<>();
    }

    public static Pins init() {
        return new Pins();
    }

    public void add(Pin pin) {
        pins.add(pin);
    }

    public Pin getBeforeLast() {
        if (pins.size() < MIN_SIZE) {
            return pins.getLast();
        }
        return pins.get(pins.size() - 2);
    }

    public Pin getLast() {
        return pins.getLast();
    }

    public int total() {
        return pins.stream().map(Pin::getFallenPinCount).reduce(Integer::sum).orElse(0);
    }

    public Pin get(int index) {
        if (index > pins.size() - 1) {
            return pins.getLast();
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
