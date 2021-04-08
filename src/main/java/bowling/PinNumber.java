package bowling;

public class PinNumber {

    private final int pinNumber;

    public PinNumber(int pinNumber) {
        if (pinNumber < 0 || pinNumber > 10) {
            throw new IllegalArgumentException();
        }
        this.pinNumber = pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public boolean isStrike() {
        return pinNumber == 10;
    }
}
