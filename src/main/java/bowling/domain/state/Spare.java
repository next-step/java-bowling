package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Spare implements State {
    private int fistFallenPins;
    private int secondFallenPins;
    private Score score;

    public Spare(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = new Score(this.fistFallenPins + this.secondFallenPins, 1);
    }

    @Override
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return this.fistFallenPins+ "|" +"/";
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(this.fistFallenPins);
        if (before.isCalculation()) {
            return before;
        }
        before = before.bowl(this.secondFallenPins);
        return before;
    }

    @Override
    public void renewScore(Score score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return fistFallenPins == spare.fistFallenPins &&
                secondFallenPins == spare.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistFallenPins, secondFallenPins);
    }
}
