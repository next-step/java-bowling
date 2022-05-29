package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Strike extends Finished {

    private static final String SCORE_MESSAGE = "X";
    private static final int MAX_PINS_COUNT = 10;
    private static final int MAX_LEFT_COUNT = 2;

    @Override
    public boolean canCalculate(Score beforeScore) {
        beforeScore.bowl(new Pins(MAX_PINS_COUNT));
        return true;
    }

    @Override
    public String mark() {
        return SCORE_MESSAGE;
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        return beforeScore.bowl(new Pins(MAX_PINS_COUNT));
    }

    @Override
    public Score score() {
        return new Score(MAX_PINS_COUNT, MAX_LEFT_COUNT);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Strike;
    }
}
