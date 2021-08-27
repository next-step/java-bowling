package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;

public class Miss implements Finished {

    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score getScore() {
        return Score.of(firstPins.getTotalPins(secondPins), 0);
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
        return firstPins + "|" + (secondPins.getPins() == 0 ? "-" : secondPins);
    }
}
