package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

class FirstBowl extends Running {
    private final Pin firstPins;

    FirstBowl(Pin pin) {
        this.firstPins = pin;
    }

    @Override
    public State bowl(Pin secondPin) {

        if (firstPins.isSpare(secondPin)) {
            return new Spare(firstPins, secondPin);
        }
        return new Miss(firstPins, secondPin);
    }

    public Score calculateAdditionalScore(Score score) {
        score = score.sumScore(firstPins);
        if (score.canCalculateScore()) {
            return score;
        }
        throw new CannotCalculateException();
    }

    @Override
    public String record() {
        return ifCountOfPinsZeroTransGutter(firstPins.count());
    }

    @Override
    public boolean canBowlFinalFrame() {
        return true;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
