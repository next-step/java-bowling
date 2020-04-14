package bowling.domain.state;

import static bowling.Constants.*;
import static bowling.Constants.WRONG_FELLED_PIN;

public class Strike implements State {
    public static final String STRIKE_CAN_NOT_PLAY_ERROR = "Strike 상태입니다. 프레임이 종료되어 플레이할 수 없습니다.";

    private static final String TEXT = "X";

    @Override
    public State play(int newFelledPin) {
        assertFirstFelledPin(newFelledPin);

        if(newFelledPin == MAX_FELLED_PIN_COUNT) {
            return new Strike();
        }
        if(newFelledPin == ZERO) {
            return new Gutter();
        }

        return new Playing(newFelledPin);
    }

    @Override
    public boolean isEndedState() {
        return true;
    }

    @Override
    public String getString() {
        return TEXT;
    }

    private void assertFirstFelledPin(int felledPin) {
        if(felledPin > MAX_FELLED_PIN_COUNT || felledPin < MIN_FELLED_PIN_COUNT) {
            throw new IllegalArgumentException(WRONG_FELLED_PIN);
        }
    }
}
