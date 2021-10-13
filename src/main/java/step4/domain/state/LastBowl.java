package step4.domain.state;

import java.util.Objects;
import step4.domain.Pins;
import step4.domain.Score;

public class LastBowl extends Finished {
    private int firstFalledPins;
    private int lastFalledPins;

    public LastBowl(int firstFalledPins, int lastFalledPins) {
        super(firstFalledPins + lastFalledPins, 0);
        this.firstFalledPins = firstFalledPins;
        this.lastFalledPins = lastFalledPins;
        Pins.validPins(firstFalledPins + lastFalledPins);
    }

    public String getSymbol() {
        return firstFalledPins + "|" + lastFalledPins;
    }

    public Score calculateScore(Score beforeScore) {

        if (beforeScore.canCalculate()) {
            return  beforeScore;
        }

        beforeScore = beforeScore.throwBowl(firstFalledPins);
        if (beforeScore.canCalculate()) {
            return  beforeScore;
        }

        beforeScore = beforeScore.throwBowl(lastFalledPins);
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
        LastBowl lastBowl = (LastBowl) o;
        return firstFalledPins == lastBowl.firstFalledPins
            && lastFalledPins == lastBowl.lastFalledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFalledPins, lastFalledPins);
    }
}
