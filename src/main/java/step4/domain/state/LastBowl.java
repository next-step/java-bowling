package step4.domain.state;

import java.util.Objects;
import step4.domain.Pins;
import step4.domain.Score;

public class LastBowl extends Finished {
    private int firstFalledPins;
    private int lastFalledPins;
    private Score score;

    public LastBowl(int firstFalledPins, int lastFalledPins) {
        this.firstFalledPins = firstFalledPins;
        this.lastFalledPins = lastFalledPins;
        this.score = new Score(firstFalledPins + lastFalledPins, 0);
        Pins.validPins(firstFalledPins + lastFalledPins);
    }

    public int getScore() {
        return score.getScore();
    }

    public Score score() {
        return score;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastBowl lastBowl = (LastBowl) o;
        return firstFalledPins == lastBowl.firstFalledPins
            && lastFalledPins == lastBowl.lastFalledPins
            && Objects.equals(score, lastBowl.score);
    }

    public int hashCode() {
        return Objects.hash(firstFalledPins, lastFalledPins, score);
    }
}
