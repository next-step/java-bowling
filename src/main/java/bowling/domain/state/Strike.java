package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class Strike extends Finished {
    private static final int MAX_FALLEN_PINS = 10;

    private Pin firstFallenPins;
    private Pin secondFallenPins;
    private String display;
    private Score score;

    public Strike(Pin firstFallenPins, Pin secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.display = String.format("X|%s", convert(secondFallenPins.getFallenPins()));
        this.score = Score.ofStrike();
    }

    public Strike() {
        this.firstFallenPins = new Pin(MAX_FALLEN_PINS);
        this.display = " X ";
        this.score = Score.ofStrike();
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
        Pin bonusPin = new Pin(pins);
        if (Objects.nonNull(secondFallenPins)) {
            return new Bonus(firstFallenPins, secondFallenPins, bonusPin);
        }
        return new Strike(firstFallenPins, bonusPin);
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
