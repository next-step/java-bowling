package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

class Strike extends Finished {

    @Override
    public boolean canBowlFinalFrame() {
        return true;
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public Score getScore() {
        return new Score(Pin.MAXIMUM_PIN_COUNT, 2);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(new Pin(Pin.MAXIMUM_PIN_COUNT));
    }

    @Override
    public String record() {
        return "X";
    }
}
