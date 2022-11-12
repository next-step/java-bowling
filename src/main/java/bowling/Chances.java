package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chances {
    private static final int MAX_CHANCE_COUNT = 2;
    private final List<Chance> chances;

    public Chances() {
        this.chances = new ArrayList<>();
    }

    public void add(int knockDownCount) {
        int order = chances.size();
        Pin pin = Pin.of(knockDownCount);
        Pin sumOfPin = chances.stream()
                .map(Chance::pin)
                .reduce(Pin.of(0), Pin::add)
                .add(pin);
        chances.add(new Chance(order, pin, Status.findStatus(order, sumOfPin.areAllPinsDown(), pin.areNoPinsDown())));
    }

    public boolean areAllPinsDown() {
        return chances.stream()
                .map(Chance::pin)
                .reduce(Pin.of(0), Pin::add)
                .areAllPinsDown();
    }

    public boolean noLeftChances() {
        return chances.size() == MAX_CHANCE_COUNT;
    }

    public List<Chance> chances() {
        return Collections.unmodifiableList(chances);
    }

}
