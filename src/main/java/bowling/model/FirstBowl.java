package bowling.model;

public class FirstBowl extends FrameState {

    private Pins pins;

    public FirstBowl(Pins pins) {
        this.pins = pins;
    }

    boolean isFinished() {
        return pins.equals(Pins.DOWN_ALL);
    }
}