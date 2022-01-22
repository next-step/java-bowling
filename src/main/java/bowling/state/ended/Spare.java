package bowling.state.ended;

import bowling.Pins;
import bowling.frame.Score;

import java.util.Objects;

public class Spare extends Ended {

    private final Pins beforePins;
    private final Pins afterPins;

    public Spare(Pins beforePins, Pins afterPins) {
        if (beforePins.totalPinCount(afterPins) != Pins.MAX_PINS_COUNT) {
            throw new IllegalArgumentException(String.format("스페어는 %d개를 쓰러트려야 합니다.", Pins.MAX_PINS_COUNT));
        }
        this.beforePins = beforePins;
        this.afterPins = afterPins;
    }

    @Override
    public String symbol() {
        return beforePins.symbol() + "|/";
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
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
        Spare spare = (Spare) o;
        return Objects.equals(beforePins, spare.beforePins) && Objects.equals(afterPins, spare.afterPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beforePins, afterPins);
    }

    @Override
    public String toString() {
        return "Spare{" +
                "beforePins=" + beforePins +
                ", afterPins=" + afterPins +
                '}';
    }
}
