package bowling.model.state;

import bowling.model.Pins;
import bowling.model.state.finished.Strike;

public class Start {
    public static State bowling(int fallenPin) {
        Pins firstScore = Pins.from(fallenPin);

        if (firstScore.isMaxScore()) {
            return Strike.from(firstScore);
        }

        return Open.from(firstScore);
    }
}
