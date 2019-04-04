package domain;

public class Pins {
    static final int MAXIMUM_PINS = 10;
    static final int MINIMUM_PINS = 0;

    private int number;

    public Pins(int number) {
        if(number > MAXIMUM_PINS) {
            throw new IllegalArgumentException(MAXIMUM_PINS + " 이하의 숫자를 입력하세요");
        }

        if(number < MINIMUM_PINS) {
            throw new IllegalArgumentException(MAXIMUM_PINS + " 이상의 숫자를 입력하세요");
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isNoPinFallen() {
        return number == 0;
    }

    public boolean areAllPinsFallen() {
        return number >= MAXIMUM_PINS;
    }

    public boolean areAllPinsFallen(Pins pins) {
        return this.number + pins.getNumber() >= MAXIMUM_PINS;
    }

}