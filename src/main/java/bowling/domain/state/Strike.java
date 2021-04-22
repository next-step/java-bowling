package bowling.domain.state;

public class Strike implements State {
    private static final String SYMBOL = "X";

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

    @Override
    public String score() {
        return SYMBOL;
    }

    @Override
    public String totalScore() {
        return SYMBOL;
    }
}
