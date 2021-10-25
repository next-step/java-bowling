package bowling.model.state;

import bowling.model.Score;

public class Spare extends Finished {
    private final Pin firstPins;
    private final Pin secondPins;

    Spare(Pin firstPins, Pin secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException();
        }

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        score = secondPins.sumScore(score);
        return score;
    }

    @Override
    public Score getScore() {
        return new Score(Pin.MAX_PINS, 1);
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }
}
