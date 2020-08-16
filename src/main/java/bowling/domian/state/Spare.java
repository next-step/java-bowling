package bowling.domian.state;

import bowling.domian.frame.Score;

public class Spare extends Finished {
    private Pins firstPins;
    private Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins= firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        return Score.spare();
    }

    @Override
    public Score calculateAdditional(Score score) {
        score = firstPins.addScore(score);

        if (!score.isCalculateDone()) {
            score = secondPins.addScore(score);
        }

        return score;
    }
}
