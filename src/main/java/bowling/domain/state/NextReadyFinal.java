package bowling.domain.state;

import bowling.domain.frame.Score;

public class NextReadyFinal implements State {
    private int fallenPins;

    public NextReadyFinal(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public State bowl(int pins) {
        if (fallenPins + pins == 10) {
            return new Spare(fallenPins, pins, false);
        }
        return new Miss(fallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return " "+fallenPins +"   ";
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(this.fallenPins);
        if (before.isCalculation()) {
            return before;
        }
        return before;
    }

    @Override
    public void renewScore(Score score) {
    }
}
