package bowling.domain.pin;

import java.util.ArrayList;
import java.util.List;

public class DownPins {

    private List<Integer> downPins;

    public DownPins() {
        this.downPins = new ArrayList<>();
    }

    public void add(int pin) {
        this.downPins.add(pin);
    }

    public List<Integer> getDownPins() {
        return new ArrayList<>(downPins);
    }
}
