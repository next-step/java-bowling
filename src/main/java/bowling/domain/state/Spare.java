package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Spare extends Finished {
    private Pin firstFallenPins;
    private Pin secondFallenPins;
    private Score score;

    public Spare(Pin firstFallenPins, Pin secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.score = Score.ofSpare();
    }

    @Override
    public String display() {
        return firstFallenPins.display(secondFallenPins);
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(firstFallenPins.getFallenPins());
        if (before.isCalculation()) {
            return before;
        }
        before = before.bowl(secondFallenPins.getFallenPins());
        return before;
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
