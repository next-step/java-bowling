package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class Spare extends Finished {

    private static final String SCORE_FORMAT = "  %s|/ |";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        validatePins(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validatePins(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException();
        }
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
    public String mark() {
        return String.format(SCORE_FORMAT, firstPins);
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
    public Score score() {
        return new Score(firstPins.totalScore(secondPins), 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPins, spare.firstPins) && Objects.equals(secondPins, spare.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
