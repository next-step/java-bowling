package bowling.state.ready;

import bowling.state.State;
import bowling.state.finish.Gutter;
import bowling.state.finish.Miss;
import bowling.state.finish.Spare;

import static java.lang.String.valueOf;

public class Second extends Ready {

    private final int firstFelledPins;

    private Second(int felledPins) {
        this.firstFelledPins = felledPins;
    }

    public static State of(int felledPins) {
        return new Second(felledPins);
    }

    @Override
    public State bowl(int secondFelledPins) {
        int felledPinsTotal = firstFelledPins + secondFelledPins;
        if (felledPinsTotal == FELLED_ALL_PINS) {
            return Spare.of(firstFelledPins);
        }
        if (felledPinsTotal == FELLED_ZERO_PINS) {
            return Gutter.of();
        }
        return Miss.of(firstFelledPins, secondFelledPins);
    }

    @Override
    public String view() {
        if (firstFelledPins == FELLED_ZERO_PINS) {
            return GUTTER;
        }
        return valueOf(firstFelledPins);
    }
}
