package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class FirstBowl extends Running {
    private static final String SCORE_FORMAT = "   %s  |";

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    @Override
    public State bowl(int fallPins) {
        Pins secondPins = new Pins(fallPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score sumBeforeScore(Score beforeScore) {
        beforeScore.bowl(firstPins);
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean canCalculate(Score beforeScore) {
        beforeScore.bowl(firstPins);
        if (beforeScore.canCalculate()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(SCORE_FORMAT, firstPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(firstPins, firstBowl.firstPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins);
    }
}
