package bowling.state.ended;

import bowling.Pins;
import bowling.frame.Score;

import java.util.Objects;

public class Miss extends Ended {

    private final Pins beforePins;
    private final Pins afterPins;

    public Miss(Pins beforePins, Pins afterPins) {
        this.beforePins = beforePins;
        this.afterPins = afterPins;
    }

    @Override
    public String symbol() {
        return beforePins.symbol() + "|" + afterPins.symbol();
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(beforePins.totalPinCount(afterPins));
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = this.beforePins.sumScore(beforeScore);
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        return afterPins.sumScore(beforeScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(beforePins, miss.beforePins) && Objects.equals(afterPins, miss.afterPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beforePins, afterPins);
    }

    @Override
    public String toString() {
        return "Miss{" +
                "beforePins=" + beforePins +
                ", afterPins=" + afterPins +
                '}';
    }
}
