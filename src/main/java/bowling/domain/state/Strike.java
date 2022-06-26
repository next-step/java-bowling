package bowling.domain.state;

import bowling.domain.Score;

public class Strike extends Finished {
    private static final String STRIKE_SYMBOL = "X";

    public Strike() {
        super(MAX_COUNT_OF_PINS, STRIKE_SYMBOL);
    }

    @Override
    public Score getScore() {
        return new Score(fallenPins, CALCULATE_TWICE);
    }
}
