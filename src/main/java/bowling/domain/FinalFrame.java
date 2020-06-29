package bowling.domain;

public class FinalFrame extends Frame {


    public FinalFrame(Pin firstPin) {
        this.firstPin = firstPin;
        this.secondPin = new Pin();

        //state
        if (firstPin.isGutter()) {
            this.state = State.GURTER;
        }
        if (firstPin.leftPins() < BOWLING_MAX_PINS) {
            this.state = State.MISS;
        }
        if (firstPin.isAllClear()) {
            this.state = State.STRIKE;
        }
    }

}
