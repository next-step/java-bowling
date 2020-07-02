package bowling.domain;

import bowling.common.IntegerUtils;

public class Pin {
    public static final int MAX_COUNT = 10;
    private int count;

    public Pin(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count > MAX_COUNT) {
            throw new IllegalArgumentException("pin count can not more than " + MAX_COUNT);
        }
    }

    public int getCount() {
        return count;
    }

    public void subtract(Pin pin) {
        count -= pin.getCount();
    }

    public boolean canSubtract(Pin pin) {
        return count >= pin.getCount();
    }

    public boolean isZeroPin() {
        return count == IntegerUtils.ZERO;
    }
}
