package bowling.domain;

import bowling.common.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class Score {
    public static final int MAX_THROW_COUNT = 2;
    private final List<Pin> pins;
    private final List<Shot> shotHistory;
    private int remain;

    public Score() {
        this.pins = new ArrayList<>();
        this.shotHistory = new ArrayList<>();
        this.remain = Pin.MAX_COUNT;
    }

    public boolean add(Pin pin) {
        validateThrow(pin);

        insertShotHistory(pin);

        pins.add(pin);
        remain -= pin.getCount();

        return pins.size() == MAX_THROW_COUNT || remain == IntegerUtils.ZERO;
    }

    private void validateThrow(Pin pin) {
        if (remain - pin.getCount() < 0) {
            throw new IllegalArgumentException("remain can not less than 0");
        }

        if (pins.size() + 1 > MAX_THROW_COUNT) {
            throw new IllegalArgumentException("Frame is finished");
        }
    }

    private void insertShotHistory(Pin pin) {
        shotHistory.add(Shot.of(pins.size() == IntegerUtils.ZERO, pin.getCount(), remain - pin.getCount()));
    }

    public List<Shot> getShotHistory() {
        return shotHistory;
    }

    public int getRemain() {
        return remain;
    }
}
