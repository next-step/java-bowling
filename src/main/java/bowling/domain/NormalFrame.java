package bowling.domain;

public class NormalFrame extends Frame {

    public NormalFrame(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        //state
        if (firstPin.isGutter() && secondPin.isGutter()) {
            this.state = State.GURTER;
        }
        if (secondPin.leftPins() < BOWLING_MAX_PINS) {
            this.state = State.MISS;
        }
        if (firstPin.isAllClear()) {
            this.state = State.STRIKE;
        }
        if (secondPin.isAllClear()) {
            this.state = State.SPARE;
        }
    }

}
