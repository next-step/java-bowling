package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class Miss extends Finished {

    private static final String SCORE_FORMAT = "  %s|%s |";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public boolean canCalculate(Score beforeScore) {
        beforeScore.bowl(firstPins);
        if (beforeScore.canCalculate()) {
            return true;
        }

        beforeScore.bowl(secondPins);
        if (beforeScore.canCalculate()) {
            return true;
        }
        return false;
    }

    @Override
    public Score score() {
        return new Score(firstPins.totalScore(secondPins), 0);
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        beforeScore.bowl(firstPins);
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        return beforeScore.bowl(secondPins);
    }

    @Override
    public String toString() {
        return String.format(SCORE_FORMAT, firstPins, secondPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPins, miss.firstPins) && Objects.equals(secondPins, miss.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
