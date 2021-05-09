package bowling.domain.state;

import bowling.domain.score.Score;

public class Spare extends Finish {

    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(final Pins firstPins, final Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static final State of(final Pins firstPins, final Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
