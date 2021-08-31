package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;

public class Spare implements Finished {

    private static final int SPARE_LEFT_COUNT = 1;

    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    @Override
    public Score getScore() {
        return Score.of(Pins.MAX_PIN_NUMBER, SPARE_LEFT_COUNT);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstPins.getScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        score = secondPins.getScore(score);
        return score;
    }

    @Override
    public String toString() {
        return firstPins +"|/";
    }
}
