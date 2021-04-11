package bowling.domain;

public class PinNumber {

    private static final int GUTTER_CONDITION = 0;
    private static final int STRIKE_CONDITION = 10;
    private final int pinNumber;

    public PinNumber(int pinNumber) {
        if (pinNumber < 0 || pinNumber > 10) {
            throw new IllegalArgumentException();
        }
        this.pinNumber = pinNumber;
    }

    public int pinNumber() {
        return pinNumber;
    }

    public boolean isStrike() {
        return pinNumber == STRIKE_CONDITION;
    }

    public boolean isGutter() {
        return pinNumber == GUTTER_CONDITION;
    }
}
