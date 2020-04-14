package bowling.domain.state;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;
import static bowling.Constants.MIN_FELLED_PIN_COUNT;

public class Gutter implements State {

    static final String TEXT = "-";

    @Override
    public State play(int newFelledPin) {

        if(newFelledPin == MAX_FELLED_PIN_COUNT) {
            return new Spare(MIN_FELLED_PIN_COUNT, newFelledPin);
        }

        return new Miss(MIN_FELLED_PIN_COUNT, newFelledPin);
    }

    @Override
    public boolean isEndedState() {
        return false;
    }

    @Override
    public String getString() {
        return TEXT;
    }

    @Override
    public boolean canAdditionalFrame() {
        return false;
    }
}
