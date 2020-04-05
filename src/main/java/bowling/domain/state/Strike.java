package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Strike extends Finished {
    private static final int MAX_FALLEN_PINS = 10;

    private int firstFallenPins;
    private Integer secondFallenPins;
    private String display;
    private Score score;

    public Strike(int firstFallenPins, int secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.display = String.format("X|%s", convert(secondFallenPins));
        this.score = Score.ofStrike();
    }

    public Strike() {
        this.firstFallenPins = MAX_FALLEN_PINS;
        this.display = " X ";
        this.score = Score.ofStrike();
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
        return before;
    }

    @Override
    public void renewScore(Score score) {
        this.score = score;
    }

    private String convert(int number) {
        if (number == MAX_FALLEN_PINS) {
            return "X";
        }
        return String.valueOf(number);
    }

    @Override
    public State bowl(int pins) {
        if (Objects.nonNull(secondFallenPins)) {
            return new Bonus(firstFallenPins, secondFallenPins, pins);
        }
        return new Strike(firstFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String display() {
        return this.display;
    }
}
