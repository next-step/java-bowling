package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Strike extends Finished {

    private static final String SCORE_MESSAGE = "  X   |";
    private static final int MAX_PINS_COUNT = 10;

    @Override
    public boolean canCalculate(Score beforeScore) {
        beforeScore.bowl(new Pins(MAX_PINS_COUNT));
        return true;
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        return beforeScore.bowl(new Pins(MAX_PINS_COUNT));
    }

    @Override
    public Score score() {
        return new Score(10, 2);
    }

    @Override
    public String toString() {
        return SCORE_MESSAGE;
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
