package bowling.domain.state;

import bowling.domain.frame.Score;

public class Strike extends Finished {
    private static final int MAX_FALLEN_PINS = 10;

    private Pin firstFallenPins;
    private Score score;

    public Strike() {
        this.firstFallenPins = new Pin(MAX_FALLEN_PINS);
        this.score = Score.ofStrike();
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public void updateScore(Score score) {
        this.score = score;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        if (before.isCalculation()) {
            return before;
        }
        return before.bowl(MAX_FALLEN_PINS);
    }

    @Override
    public String display() {
        return firstFallenPins.display();
    }
}
