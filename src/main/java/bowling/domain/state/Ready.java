package bowling.domain.state;

import static bowling.Constants.*;

public class Ready implements State {

    @Override
    public State play(int felledPin) {
        if(felledPin == MAX_FELLED_PIN_COUNT) {
            return new Strike();
        }
        if(felledPin == ZERO) {
            return new Gutter();
        }

        return new Playing(felledPin);
    }
}
