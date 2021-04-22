package bowling.domain.state;

public class Miss implements State {
    private final BowlingPin firstPin;
    private final BowlingPin secondPin;

    public Miss(BowlingPin firstPin) {
        this.firstPin = firstPin;
        this.secondPin = BowlingPin.of(0);
    }
    public Miss(BowlingPin firstPin, BowlingPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }
}
