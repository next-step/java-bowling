package step2.domain.state;

import step2.domain.Pins;
import step2.domain.Score;

public class Spare extends Finished {

    private static final int ZERO_CHANCE = 0;
    private static final String DELIMITER = "|";
    private static final String SPARE_SYMBOL = "/";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
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
        return firstPins.getFallingPins() + DELIMITER + SPARE_SYMBOL;
    }
}
