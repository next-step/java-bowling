package bowling.domain.pin;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int PINS_LIMIT = 10;

    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public int getTotalPins() {
        return this.pins.stream()
                .map(Pin::getPin)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getLeftPin() {
        return PINS_LIMIT - getTotalPins();
    }

    public void addPins(int pin) {
        this.pins.add(new Pin(pin));
    }

    public List<Pin> getPins() {
        return pins;
    }

    public int getPinCount(int index) {
        return pins.get(index).getPin();
    }

    public int rollCount() {
        return this.pins.size();
    }



}
