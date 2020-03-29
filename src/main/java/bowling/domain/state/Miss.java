package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Miss implements State {
    private int firstFallenPins;
    private int secondFallenPins;
    private Score score;

    public Miss(int firstFallenPins, int secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = new Score(this.firstFallenPins + this.secondFallenPins, 0);
    }

    public Score getScore() {
        return this.score;
    }

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
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return this.firstFallenPins + "|" + this.secondFallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return firstFallenPins == miss.firstFallenPins &&
                secondFallenPins == miss.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPins, secondFallenPins);
    }
}
