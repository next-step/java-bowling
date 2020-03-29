package bowling.domain.state;

import bowling.domain.frame.Score;

public class Strike implements State {

    private int firstFallenPins;
    private String display;
    private boolean finish;
    private Score score;

    public Strike(int firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.display = "X    ";
        this.finish = false;
        this.score = new Score(firstFallenPins, 2);
    }

    public Strike() {
        this.firstFallenPins = 10;
        this.display = " X ";
        this.finish = true;
        this.score = new Score(firstFallenPins, 2);
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
        if (number == 10) {
            return "X";
        }
        return String.valueOf(number);
    }

    @Override
    public State bowl(int pins) {
        return new Bonus(firstFallenPins, pins);
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
