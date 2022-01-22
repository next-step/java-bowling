package bowling.state.running;

import bowling.Pins;
import bowling.exception.CannotScoreCalculateException;
import bowling.frame.Score;
import bowling.state.Throwing;
import bowling.state.ended.Miss;
import bowling.state.ended.Spare;

import java.util.Objects;

public class FirstBowl extends Running {

    private final Pins fallenPins;

    public FirstBowl(Pins fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public Throwing bowl(Pins afterPins) {
        if (fallenPins.isSpare(afterPins)) {
            return new Spare(fallenPins, afterPins);
        }
        return new Miss(fallenPins, afterPins);
    }

    @Override
    public String symbol() {
        return String.valueOf(fallenPins.getFallenPins());
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = fallenPins.sumScore(beforeScore);
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(fallenPins, firstBowl.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }

    @Override
    public String toString() {
        return "FirstBowl{" +
                "fallenPins=" + fallenPins +
                '}';
    }
}
