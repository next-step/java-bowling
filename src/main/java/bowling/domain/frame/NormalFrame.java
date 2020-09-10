package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int FIRST_FRAME = 1;
    private static final int PINS_LIMIT = 10;
    private static final String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";
    private static final int CAN_ROLL_LIMIT = 2;
    private static final int FIRST_ROLL = 1;

    private Pins pins;
    private int index;

    public NormalFrame(final int index) {
        this.pins = new Pins();
        this.index = index;
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    @Override
    public void roll(int pin) {
        if (isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }
        if (canRoll()) {
            pins.addPins(pin);
        }
    }

    @Override
    public boolean canRoll () {
        if (isAlreadyStrike() || isRolledTwice()) {
            return false;
        }
        return true;
    }

    private boolean isRolledTwice() {
        return pins.rollCount() >= CAN_ROLL_LIMIT;
    }
    private boolean isAlreadyStrike() {
        return pins.rollCount() == FIRST_ROLL && pins.getTotalPins() == PINS_LIMIT;
    }
    private boolean isPinTotalOverTen(int pin) {
        return this.getPins() + pin > PINS_LIMIT;
    }

    @Override
    public int getPins() {
        return this.pins.getTotalPins();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        NormalFrame that = (NormalFrame) o;
        return getIndex() == that.getIndex() &&
                Objects.equals(getPins(), that.getPins());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPins(), getIndex());
    }
}
