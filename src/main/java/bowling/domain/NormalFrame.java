package bowling.domain;

public class NormalFrame extends Frame {

    public NormalFrame(Pins pins) {
        this.pins = pins;
        //state
        if (pins.getFirstPin().isGutter() && pins.getSecondPin().isGutter()) {
            this.state = State.GURTER;
        }
        if (pins.getSecondPin().leftPins() < BOWLING_MAX_PINS) {
            this.state = State.MISS;
        }
        if (pins.getSecondPin().isAllClear()) {
            this.state = State.SPARE;
        }
        if (pins.getFirstPin().isAllClear()) {
            this.state = State.STRIKE;
        }
    }

}
