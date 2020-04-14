package bowling.domain.state;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;

public class Playing implements State{

    private final int felledPin;

    public Playing(int felledPin) {
        this.felledPin = felledPin;
    }

    @Override
    public State play(int felledPin) {
        if(this.felledPin + felledPin == MAX_FELLED_PIN_COUNT) {
            return new Spare(this.felledPin, felledPin);
        }

        return new Miss(this.felledPin, felledPin);
    }
}
