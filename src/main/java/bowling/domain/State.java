package bowling.domain;

import java.util.Objects;

public class State {
    private Pins firstPins;
    private Pins secondPins;

    public void bowl(int fallenPins) {
        if (Objects.isNull(firstPins)) {
            firstPins = Pins.of(fallenPins);
            return;
        }

        if (!Objects.isNull(firstPins)) {
            secondPins = Pins.of(fallenPins);
        }
    }

    public Pins getFirstPin() {
        return firstPins;
    }

    public boolean isFinish() {
        if (Objects.isNull(firstPins)) {
            return false;
        }
        if (firstPins.isStrike()) {
            return true;
        }
        if (!Objects.isNull(secondPins)) {
            return true;
        }
        return false;
    }
}
