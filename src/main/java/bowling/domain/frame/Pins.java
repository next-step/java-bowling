package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private final List<Integer> pins;

    public Pins() {
        this(new ArrayList<>());
    }

    public Pins(List<Integer> pins) {
        this.pins = pins;
    }

    public void add(int fallenPins) {
        pins.add(fallenPins);
    }

    public int size() {
        return pins.size();
    }

    public int first() {
        return pins.get(0);
    }

    public int second() {
        return pins.get(1);
    }

    public int third() {
        return pins.get(2);
    }
}
