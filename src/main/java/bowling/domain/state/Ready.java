package bowling.domain.state;

import static bowling.Constants.*;

public class Ready implements State {

    @Override
    public State play(int newFelledPin) {
        if(newFelledPin == MAX_FELLED_PIN_COUNT) {
            return new Strike();
        }
        if(newFelledPin == ZERO) {
            return new Gutter();
        }

        return new Playing(newFelledPin);
    }
}
