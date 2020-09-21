package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.ScoreConverter;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
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

    public NormalFrame() {
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    public static NormalFrame frame() {
        return new NormalFrame();
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
    public boolean canRoll() {
        return !isAlreadyStrike() && !isRolledTwice();
    }

    private boolean isRolledTwice() {
        return pins.rollCount() >= CAN_ROLL_LIMIT;
    }

    private boolean isAlreadyStrike() {
        return pins.rollCount() == FIRST_ROLL && pins.getTotalPins() == PINS_LIMIT;
    }

    private boolean isPinTotalOverTen(int pin) {
        return this.getTotal() + pin > PINS_LIMIT;
    }

    @Override
    public int getTotal() {
        return this.pins.getTotalPins();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public List<Pin> getPinInfo() {
        return pins.getPins();
    }

    @Override
    public Pins getPins() { return pins;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        NormalFrame that = (NormalFrame) o;
        return getIndex() == that.getIndex() &&
                Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotal(), getIndex());
    }

    @Override
    public String getScore() { return ScoreConverter.convert(this); }

}
