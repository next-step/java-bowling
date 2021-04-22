package bowling.domain.state;

public class Strike implements State {
    private final BowlingPin bowlingPin;

    private Strike(BowlingPin bowlingPin) {
        this.bowlingPin = bowlingPin;
    }

    public static Strike of(BowlingPin bowlingPin) {
        return new Strike(bowlingPin);
    }

    @Override
    public BowlingPin firstHit() {
        return bowlingPin;
    }
}
