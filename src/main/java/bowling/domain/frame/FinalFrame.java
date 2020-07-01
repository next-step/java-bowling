package bowling.domain.frame;

import bowling.domain.pin.Pins;



public class FinalFrame implements Frame {
    private static final int PINS_LIMIT = 10;
    private static final int FIRST_ROLL = 1;
    private static final int SECOND_ROLL = 2;
    private static final int CAN_ROLL_LIMIT = 2;
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";

    private Pins pins;
    private int index;

    public FinalFrame() {
        this.pins = new Pins();
        this.index = 9;
    }

    @Override
    public void roll(int pin) {

        if (isRolledTwice() && (!isAlreadySpare() || !isAlreadyStrike()) ) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }

        if (pins.rollCount() == 1 && (!isAlreadySpare() || !isAlreadyStrike()) && pins.getTotalPins() + pin > 10) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
        pins.addPins(pin);
    }

    @Override
    public int getPins() {
        return this.pins.getTotalPins();
    }

    private boolean isAlreadyStrike() {
        return pins.rollCount() < 2 && pins.getTotalPins() == PINS_LIMIT;
    }

    private boolean isAlreadySpare() {
        return pins.rollCount() < 2 && pins.getTotalPins() == PINS_LIMIT;
    }

    private boolean isRolledTwice() {
        return pins.rollCount() == CAN_ROLL_LIMIT;
    }
}
