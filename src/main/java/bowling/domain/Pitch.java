package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class Pitch {
    public static final int MAX_THROW_COUNT = 2;
    private final List<Pin> pins;
    private final ShotHistory shotHistory;
    private final Pin remainPin;

    public Pitch() {
        this.pins = new ArrayList<>();
        this.shotHistory = new ShotHistory(new ArrayList<>());
        this.remainPin = new Pin(Pin.MAX_COUNT);
    }

    public void add(Pin pin) {
        validateThrow(pin);

        insertShotHistory(pin);

        pins.add(pin);
        remainPin.subtract(pin);
    }

    private void validateThrow(Pin pin) {
        if (!remainPin.canSubtract(pin)) {
            throw new IllegalArgumentException("remain can not less than 0");
        }

        if (!canThrowOneMore()) {
            throw new IllegalArgumentException("Frame is finished");
        }
    }

    private boolean canThrowOneMore() {
        return pins.size() + 1 <= MAX_THROW_COUNT;
    }

    private void insertShotHistory(Pin pin) {
        shotHistory.add(Shot.of(pins.size() == IntegerUtils.ZERO, pin.getCount(), remainPin.getCount() - pin.getCount()));
    }

    public int getThrowCount() {
        return pins.size();
    }

    public boolean isFinish() {
        return pins.size() == MAX_THROW_COUNT || remainPin.isZeroPin();
    }

    public ShotHistory getShotHistory() {
        return shotHistory;
    }

    public int getRemain() {
        return remainPin.getCount();
    }
}
