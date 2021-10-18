package step4.domain.state;

import java.util.Objects;
import step4.domain.Pins;
import step4.domain.Score;

public class Strike extends Finished {
    private int falledPins;

    public Strike() {
        super(10, 2);
        this.falledPins = 10;
        Pins.validPins(falledPins);
    }

    public String getSymbol() {
        return "X";
    }

    @Override
    public Score calculateScore(Score beforeScore) {

        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }
        beforeScore = beforeScore.throwBowl(10);
        return beforeScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Strike strike = (Strike) o;
        return falledPins == strike.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
