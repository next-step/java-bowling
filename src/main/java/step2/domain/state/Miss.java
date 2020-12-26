package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class Miss extends Finished {

    private static final int ZERO_CHANCE = 0;

    private Pins firstPins;
    private Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        int totalScore = firstPins.getFallingPins() + secondPins.getFallingPins();
        return Score.of(totalScore, ZERO_CHANCE);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.validateChance()) {
            return score;
        }
        score = secondPins.sumScore(score);
        return score;
    }

    @Override
    public String toString() {
        return firstPins + "|" + secondPins;
    }
}
