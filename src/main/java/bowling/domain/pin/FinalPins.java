package bowling.domain.pin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FinalPins extends Pins {

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
        // TODO: Implement it
    }
}
