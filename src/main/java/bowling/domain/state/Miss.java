package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Miss extends Finished {
    private Pin firstFallenPins;
    private Pin secondFallenPins;
    private Score score;

    public Miss(Pin firstFallenPins, Pin secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = Score.ofMiss(firstFallenPins.getFallenPins() + secondFallenPins.getFallenPins());
    }

    public Score getScore() {
        return this.score;
    }

    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(firstFallenPins.getFallenPins());
        if (before.isCalculation()) {
            return before;
        }
        before = before.bowl(secondFallenPins.getFallenPins());
        return before;
    }

    @Override
    public void renewScore(Score score) {
        this.score = score;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return firstFallenPins.getFallenPins() + "|" + secondFallenPins.getFallenPins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstFallenPins, miss.firstFallenPins) &&
                Objects.equals(secondFallenPins, miss.secondFallenPins) &&
                Objects.equals(score, miss.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPins, secondFallenPins, score);
    }
}
