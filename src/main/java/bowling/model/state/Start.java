package bowling.model.state;

import bowling.model.Pins;
import bowling.model.state.finished.Strike;

public class Start {
    public static State bowling(Pins fallenPin) {
        if (fallenPin.isMaxScore()) {
            return Strike.from(fallenPin);
        }

        return Open.from(fallenPin);
    }
}
