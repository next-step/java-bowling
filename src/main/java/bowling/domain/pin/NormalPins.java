package bowling.domain.pin;

import bowling.exception.FramePinCountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class NormalPins extends Pins {

    private static final int MAX_NORMAL_PIN_COUNT = 10;

    private NormalPins() {
        this(new ArrayList<>());
    }

    private NormalPins(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private NormalPins(List<Pin> pins) {
        super(pins);
    }

    public static NormalPins create() {
        return new NormalPins();
    }

    public static NormalPins of(Pin... pins) {
        return new NormalPins(pins);
    }

    public static NormalPins from(List<Pin> pins) {
        return new NormalPins(pins);
    }

    @Override
    public void validatePinCount(Pin pin) {
        if (totalPinCount() + pin.pinCount() > MAX_NORMAL_PIN_COUNT) {
            throw new FramePinCountException();
        }
    }
}
