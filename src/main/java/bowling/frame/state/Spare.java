package bowling.frame.state;

import bowling.frame.Score;

class Spare extends Finished {
    private final Pins firstPins;
    private final Pins secondPins;

    Spare(Pins firstPins, Pins secondPins) {
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
        return new Score(Pins.MAX_PINS, 1);
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }
}
