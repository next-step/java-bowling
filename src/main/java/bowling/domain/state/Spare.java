package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Spare implements State {
    private int firstFallenPins;
    private int secondFallenPins;
    private boolean finish;
    private Score score;
    private String display;

    public Spare(int firstFallenPins, int secondFallenPins) {
        this(firstFallenPins, secondFallenPins, true, new Score(firstFallenPins + secondFallenPins, 1), firstFallenPins + "|" + "/");
    }

    public Spare(int firstFallenPins, int secondFallenPins, boolean finish) {
        this(firstFallenPins, secondFallenPins, finish, new Score(firstFallenPins + secondFallenPins, 1), firstFallenPins + "|" + "/  ");
    }

    public Spare(int firstFallenPins, int secondFallenPins, boolean finish, Score score, String display) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.finish = finish;
        this.score = score;
        this.display = display;
    }

    @Override
    public State bowl(int pins) {
        return new Bonus(firstFallenPins, secondFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return finish;
    }

    @Override
    public String display() {
        return this.display;
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
