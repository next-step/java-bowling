package bowling.domain.state;

public class Spare implements State {
    public static final String PARTITION = "|";
    private static final String SYMBOL = "/";

    private final BowlingPin firstPin;
    private final BowlingPin secondPin;

    private Spare(BowlingPin firstPin, BowlingPin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Spare of(BowlingPin firstPin, BowlingPin secondPin) {
        return new Spare(firstPin, secondPin);
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
        return firstPin.score() + PARTITION + SYMBOL;
    }
}
