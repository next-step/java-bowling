package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Spare extends Finished {
    private int firstFallenPins;
    private int secondFallenPins;
    private Score score;

    public Spare(int firstFallenPins, int secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = new Score(firstFallenPins + secondFallenPins, 1);
    }

    @Override
    public State bowl(int pins) {
        return new Bonus(firstFallenPins, secondFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return this.firstFallenPins + "|" + "/";
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(this.firstFallenPins);
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
        return firstFallenPins == spare.firstFallenPins &&
                secondFallenPins == spare.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPins, secondFallenPins);
    }
}
