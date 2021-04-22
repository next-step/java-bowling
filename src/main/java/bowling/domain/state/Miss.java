package bowling.domain.state;

public class Miss implements State {
    private final BowlingPin firstPin;
    private final BowlingPin secondPin;

    private Miss(BowlingPin firstPin, BowlingPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Miss of(BowlingPin bowlingPin) {
        return new Miss(bowlingPin, BowlingPin.of(0));
    }

    public static Miss of(BowlingPin firstPin, BowlingPin secondPin) {
        return new Miss(firstPin, secondPin);
    }

    @Override
    public BowlingPin firstHit() {
        return firstPin;
    }
}
