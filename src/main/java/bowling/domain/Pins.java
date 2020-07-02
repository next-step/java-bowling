package bowling.domain;

public class Pins {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private Pin firstPin = new Pin();
    private Pin secondPin = new Pin();
    private Pin thirdPin = new Pin();

    public Pin getFirstPin() {
        return firstPin;
    }

    public int getFirstFallenPins() {
        return firstPin.fallenPins();
    }

    public int getFirstLeftPins() {
        return firstPin.leftPins();
    }

    public int getSecondFallenPins() {
        return secondPin.fallenPins();
    }

    public int getSecondLeftPins() {
        return secondPin.leftPins();
    }

    public int getThirdFallenPins() {
        return thirdPin.fallenPins();
    }

    public void setFirstPin(Pin firstPin) {
        this.firstPin = firstPin;
    }

    public void setSecondPin(Pin secondPin) {
        this.secondPin = secondPin;
    }

    public Pin getSecondPin() {
        return secondPin;
    }

    public Pin getThirdPin() {
        return thirdPin;
    }

    public boolean isFirstClean() {
        return firstPin.isAllClear();
    }

    public boolean isSecondClean() {
        return secondPin.isAllClear();
    }

    public boolean isThirdClean() {
        return thirdPin.isAllClear();
    }

    public int secondLeftPins() {
        return secondPin.leftPins();
    }

    public void setThirdPin(Pin thirdPin) {
        this.thirdPin = thirdPin;
    }

    public boolean isGutter() {
        return firstPin.isGutter() && secondPin.isGutter();
    }

    public boolean isSpare() {
        return !firstPin.isAllClear() && secondPin.isAllClear();
    }

    public boolean isMiss() {
        return !firstPin.isAllClear() && !secondPin.isAllClear();
    }

    public boolean isStrike() {
        return firstPin.isAllClear();
    }

    public boolean isDouble() {
        return isStrike() && secondPin.isAllClear();
    }

    public boolean isTurkey() {
        return isDouble() && thirdPin.isAllClear();
    }

}
