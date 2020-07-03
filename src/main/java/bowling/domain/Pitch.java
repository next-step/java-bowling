package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class Pitch {
    public static final int MAX_NORMAL_THROW_COUNT = 2;
    public static final int MAX_BONUS_THROW_COUNT = 1;

    private final int maxThrowCount;
    private final List<Pin> pins;
    private final ShotHistory shotHistory;
    private final Pin remainPin;

    private Pitch(int maxThrowCount) {
        this.maxThrowCount = maxThrowCount;
        this.pins = new ArrayList<>();
        this.shotHistory = new ShotHistory(new ArrayList<>());
        this.remainPin = new Pin(Pin.MAX_COUNT);
    }

    public static Pitch of() {
        return new Pitch(MAX_NORMAL_THROW_COUNT);
    }

    public static Pitch ofBounus() {
        return new Pitch(MAX_BONUS_THROW_COUNT);
    }

    public void add(Pin pin) {
        validateThrow(pin);

        pins.add(pin);
        remainPin.subtract(pin);

        insertShotHistory(pin);
    }

    private void validateThrow(Pin pin) {
        if (!remainPin.canSubtract(pin)) {
            throw new IllegalArgumentException("remain can not less than 0");
        }

        if (!canThrowOneMore()) {
            throw new IllegalArgumentException("Pitch is finished");
        }
    }

    private boolean canThrowOneMore() {
        return pins.size() + 1 <= maxThrowCount;
    }

    private void insertShotHistory(Pin pin) {
        shotHistory.add(Shot.of(isFirstShot(), pin, remainPin));
    }

    private boolean isFirstShot() {
        return pins.size() == 1;
    }

    public int getThrowCount() {
        return pins.size();
    }

    public boolean isFinish() {
        return pins.size() == maxThrowCount || remainPin.isZeroPin();
    }

    public int calculatePinCount(int count) {
        if (pins.size() < count) {
            throw new IllegalArgumentException("size of pin is less than count");
        }

        return pins
                .subList(IntegerUtils.ZERO, count)
                .stream()
                .map(Pin::getCount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public ShotHistory getShotHistory() {
        return shotHistory;
    }

    public int getFallenPin() {
        return pins.stream().map(Pin::getCount).reduce(Integer::sum).orElse(0);
    }

    public int getRemain() {
        return remainPin.getCount();
    }

    public boolean isStrikeOrSpare() {
        return remainPin.getCount() == IntegerUtils.ZERO;
    }
}
