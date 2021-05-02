package bowling.domain.pin;

import bowling.exception.PinsCountExceededException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FinalPins extends Pins {

    private static final int MAIN_GAME_COUNT = 2;
    private static final int THIRD_THROW_THRESHOLD = 10;

    private FinalPins() {
        this(new ArrayList<>());
    }

    private FinalPins(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private FinalPins(List<Pin> pins) {
        super(pins);
    }

    public static FinalPins create() {
        return new FinalPins();
    }

    public static FinalPins of(Pin... pins) {
        return new FinalPins(pins);
    }

    public static FinalPins from(List<Pin> pins) {
        return new FinalPins(pins);
    }

    @Override
    public void validatePinCount(Pin pin) {
        if (isThirdThrow() && sumOfTwoPinsCount() < THIRD_THROW_THRESHOLD) {
            throw new PinsCountExceededException();
        }
        if (isThirdThrow() && isLastGameSpare()) {
            secondPin().sum(pin);
        }
    }

    private boolean isThirdThrow() {
        return pins.size() == MAIN_GAME_COUNT;
    }

    private int sumOfTwoPinsCount() {
        return firstPin().pinCount() + secondPin().pinCount();
    }

    private boolean isLastGameSpare() {
        return firstPin().isMaximum() && !secondPin().isMaximum();
    }
}
