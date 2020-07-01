package bowling.domain;

public class Pins {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private Pin firstPin;
    private Pin secondPin;
    private Pin thirdPin;
    private boolean isFinal = false;

    public Pins(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public Pins(Pin firstPin, Pin secondPin, Pin thirdPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        this.thirdPin = thirdPin;
        this.isFinal = true;
    }

    public Pin getFirstPin() {
        return firstPin;
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

    public void setThirdPin(Pin thirdPin) {
        this.thirdPin = thirdPin;
        this.isFinal = true;
    }

    public boolean isFinal() {
        return isFinal;
    }

}
