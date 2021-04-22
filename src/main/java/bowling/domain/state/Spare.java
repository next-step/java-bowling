package bowling.domain.state;

public class Spare implements State {
    private final BowlingPin firstPin;
    private final BowlingPin secondPin;

    private Spare(BowlingPin firstPin, BowlingPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Spare of(BowlingPin firstPin, BowlingPin secondPin) {
        return new Spare(firstPin, secondPin);
    }
}
