package bowling.domain.state;


import bowling.domain.Pin;
import bowling.domain.Score;

class Spare extends Finished {

    private static final String BOWLING_STATUS_SPARE = "/";

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
        score = score.sumScore(firstPins);
        if (score.canCalculateScore()) {
            return score;
        }
        score = score.sumScore(secondPins);
        return score;
    }

    @Override
    public Score getScore() {
        return new Score(Pin.MAXIMUM_PIN_COUNT, 1);
    }

    @Override
    public String record() {
        String prevRecord = ifCountOfPinsZeroTransGutter(firstPins.count()) + "|";
        return prevRecord + BOWLING_STATUS_SPARE;
    }
}
