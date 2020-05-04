package bowling;

import java.util.ArrayList;
import java.util.List;

public class LastFrame {
    private final List<Pins> fallenPins = new ArrayList<>();

    public LastFrame bowl(int fallenPinCount) {
        Pins pins = new Pins();
        pins.bowl(fallenPinCount);
        fallenPins.add(pins);

        return this;
    }

    public boolean isGameEnd() {
        return fallenPins.size() == 2;
    }
}
