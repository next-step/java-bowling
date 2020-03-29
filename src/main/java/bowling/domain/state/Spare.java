package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Spare implements State {
    private int fistFallenPins;
    private int secondFallenPins;
    private boolean finish;
    private Score score;

    public Spare(int fistFallenPins, int secondFallenPins) {
        this(fistFallenPins, secondFallenPins, true, new Score(fistFallenPins + secondFallenPins, 1));
    }

    public Spare(int fistFallenPins, int secondFallenPins, boolean finish) {
        this(fistFallenPins, secondFallenPins, finish, new Score(fistFallenPins + secondFallenPins, 1));
    }

    public Spare(int fistFallenPins, int secondFallenPins, boolean finish, Score score) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.finish = finish;
        this.score = score;
    }

    @Override
    public State bowl(int pins) {
        if (pins == 10) {
            new Strike();
        }
        return new Miss(pins, 0);
    }

    @Override
    public boolean isFinish() {
        return finish;
    }

    @Override
    public String display() {
        return this.fistFallenPins + "|" + "/";
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
