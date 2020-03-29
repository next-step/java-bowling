package bowling.domain.state;

import bowling.domain.frame.Score;

public class Strike implements State {

    private int fistFallenPins;
    private int secondFallenPins;
    private String display;
    private boolean finish;
    private Score score;

    public Strike(int fistFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.display = "X";
        this.finish = false;
    }

    public Strike(int fistFallenPins, int secondFallenPins) {
        this.fistFallenPins = fistFallenPins;
        this.secondFallenPins = secondFallenPins;
        this.display = String.format("X|%s", convert(secondFallenPins));
        this.finish = true;
    }

    public Strike() {
        this.fistFallenPins = 10;
        this.display = "X";
        this.finish = true;
        this.score = new Score(fistFallenPins, 2);
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
        return before;
    }

    @Override
    public void renewScore(Score score) {
        this.score = score;
    }

    private String convert(int number) {
        if (number == 10) {
            return "X";
        }
        return String.valueOf(number);
    }

    @Override
    public State bowl(int pins) {
        return new Strike(this.fistFallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return this.finish;
    }

    @Override
    public String display() {
        return this.display;
    }
}
