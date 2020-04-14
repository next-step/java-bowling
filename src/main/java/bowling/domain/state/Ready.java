package bowling.domain.state;

import static bowling.Constants.MAX_FALL_PIN_COUNT;
import static bowling.Constants.ZERO;

public class Ready implements State {

    @Override
    public State play(int falledPin) {
        if(falledPin == MAX_FALL_PIN_COUNT) {
            return new Strike();
        }
        if(falledPin == ZERO) {
            return new Gutter();
        }

        return new Playing(falledPin);
    }
}
