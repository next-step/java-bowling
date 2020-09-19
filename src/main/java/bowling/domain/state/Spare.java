package bowling.domain.state;


import bowling.domain.Pin;
import bowling.domain.Score;

class Spare extends Finished {
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
    public boolean canBowlFinalFrame() {
        return true;
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
        return new Score(Pin.MAXIMUM_PIN_COUNT, 1);
    }

    @Override
    public String record() {
        return firstPins.record(secondPins);
    }
}
