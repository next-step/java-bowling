package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Miss implements State {
    private int fistFallenPins;
    private int secondFallenPins;
    private Score score;

    public Miss(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = new Score(this.fistFallenPins + this.secondFallenPins, 0);
    }

    public Score getScore() {
        return this.score;
    }

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
    public State bowl(int pins) {
        return null;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return this.fistFallenPins + "|" + this.secondFallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return fistFallenPins == miss.fistFallenPins &&
                secondFallenPins == miss.secondFallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fistFallenPins, secondFallenPins);
    }
}
