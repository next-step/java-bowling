package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GutterBowl extends FinishedBowl {

    private static final BowlType BOWL_TYPE = BowlType.GUTTER;
    private static final GutterBowl CACHED_BOWL = new GutterBowl();

    private final Pin firstPin;
    private final Pin secondPin;

    public GutterBowl() {
        this(Pin.from(0), Pin.from(0));
    }

    private GutterBowl(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Bowl bowl() {
        return CACHED_BOWL;
    }

    @Override
    public boolean typeEquals(BowlType bowlType) {
        return BOWL_TYPE.equals(bowlType);
    }

    @Override
    public List<Pin> pins() {
        List<Pin> pins = new ArrayList<>();
        pins.add(firstPin);
        pins.add(secondPin);
        return Collections.unmodifiableList(pins);
    }
}
