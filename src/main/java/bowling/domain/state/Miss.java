package bowling.domain.state;

public class Miss implements State {
    public static final String PARTITION = "|";

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

    @Override
    public String score() {
        return firstPin.score();
    }

    @Override
    public String totalScore() {
        return firstPin.score() + PARTITION + secondPin.score();
    }
}
