package bowling.domain.engine;

import static bowling.domain.Constants.BOWLING_PRINT_GUTTER;
import static bowling.domain.pin.Pin.BOWLING_PIN_MIN_SIZE;

public interface State {

    State bowl(final int pin);

    String printResult();

    String printLastResult();

    default String printScore(final int pin) {
        if (pin == BOWLING_PIN_MIN_SIZE) {
            return BOWLING_PRINT_GUTTER;
        }
        return String.valueOf(pin);
    }
}
