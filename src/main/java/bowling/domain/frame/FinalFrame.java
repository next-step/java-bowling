package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.ScoreConverter;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int PINS_LIMIT = 10;
    private static final int CAN_ROLL_LIMIT = 2;
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    public static final int TOTAL_PIN_COUNT = 10;
    public static final int MAXIMUM_CAN_ROLL = 3;

    private Pins pins;
    private int index;

    public FinalFrame() {
        this.pins = new Pins();
        this.index = 10;
    }

    @Override
    public void roll(int pin) {
        if (isPinOverflowRolledOnce(pin)) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
        if (canRoll()) {
            pins.addPins(pin);
        }
    }

    private boolean isPinOverflowRolledOnce(int pin) {
        return isRolledOnce() && (isPinUnderTen()) && pins.getTotalPins() + pin > TOTAL_PIN_COUNT;
    }

    @Override
    public boolean canRoll() {
        if (isRolledTwice() && pins.getTotalPins() < PINS_LIMIT) {
            return false;
        }
        return pins.rollCount() < MAXIMUM_CAN_ROLL;
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
    public Pins getPins() {
        return pins;
    }

    private boolean isPinUnderTen() {
        return pins.getTotalPins() < PINS_LIMIT;
    }

    private boolean isRolledTwice() {
        return pins.rollCount() == CAN_ROLL_LIMIT;
    }

    private boolean isRolledOnce() {
        return pins.rollCount() < CAN_ROLL_LIMIT;
    }

    @Override
    public String getScore() {
        return ScoreConverter.convert(this);
    }
}
