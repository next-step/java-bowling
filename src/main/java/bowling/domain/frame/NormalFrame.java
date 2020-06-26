package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.Objects;

public class NormalFrame {
    private static final int FIRST_FRAME = 1;
    private static final int PINS_LIMIT = 10;
    private static final String PIN_MAX_ERROR = "핀의 합계가 10개보다 클 수 없습니다.";
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final int CAN_ROLL_LIMIT = 2;
    private static final int FIRST_ROLL = 1;

    private Pins pins;
    private int index;

    private NormalFrame(final int index) {
        this.pins = new Pins();
        this.index = index;
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    public NormalFrame nextFrame() {
        return new NormalFrame(index + 1);
    }

    public void roll(int pin) {
        if (isRolledTwice()) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }

        if (isAlreadyStrike()) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }

        if (isPinTotalOverTen(pin)) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }

        pins.addPins(pin);
    }

    private boolean isRolledTwice() {
        return pins.rollCount() == CAN_ROLL_LIMIT;
    }

    private boolean isAlreadyStrike() {
        return pins.rollCount() == FIRST_ROLL && pins.getTotalPins() == PINS_LIMIT;
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getPins() + pin > PINS_LIMIT;
    }

    public int getPins() {
        return this.pins.getTotalPins();
    }

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
