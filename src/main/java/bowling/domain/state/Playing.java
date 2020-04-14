package bowling.domain.state;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;

public class Playing implements State{

    private final int felledPin;

    public Playing(int felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(int newFelledPin) {
        if(felledPin + newFelledPin == MAX_FELLED_PIN_COUNT) {
            return new Spare(felledPin, newFelledPin);
        }

        return new Miss(felledPin, newFelledPin);
    }
}
