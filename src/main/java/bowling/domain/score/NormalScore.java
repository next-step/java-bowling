package bowling.domain.score;

import bowling.exception.score.PinSecondValueException;
import java.util.Objects;

public class NormalScore extends Score {

    private static final Pin STRIKE = Pin.of(10);

    private NormalScore(Pin first, Pin second) {
        super(first, second);
    }

    public static NormalScore empty() {
        return new NormalScore(null, null);
    }

    @Override
    public NormalScore nextPin(Pin pin) {
        if (Objects.isNull(first)) {
            return createFirstPin(pin);
        }
        return createSecondPin(pin);
    }

    private NormalScore createFirstPin(Pin pin){
        return new NormalScore(pin, null);
    }

    private NormalScore createSecondPin(Pin pin) {
        checkFirstStrike(first);
        checkRemainPin(first, pin);
        return new NormalScore(first, pin);
    }

    private static void checkFirstStrike(Pin pin) {
        if (pin == STRIKE) {
            throw new PinSecondValueException();
        }
    }

    private static void checkRemainPin(Pin first, Pin second) {
        if (first.remainPin() < second.value()) {
            throw new PinSecondValueException();
        }
    }

    @Override
    public boolean isNext() {
        return Objects.isNull(first) || first != STRIKE;
    }

}
