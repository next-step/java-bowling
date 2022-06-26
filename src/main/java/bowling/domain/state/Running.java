package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.InvalidScoreException;

public abstract class Running extends AbstractState {

    Running() {

    }

    Running(int fallenPins, String symbol) {
        super(fallenPins, symbol);
    }

    @Override
    public Score getScore() {
        return new Score();
    }

    AbstractState bowlSecond(int secondBowl) {
        validSecondFallenPins(secondBowl);
        if (MIN_COUNT_OF_PINS == secondBowl) {
            return new Gutter();
        }

        if (this.fallenPins + secondBowl == MAX_COUNT_OF_PINS) {
            return new Spare(this.fallenPins, secondBowl);
        }

        return new Miss(this.fallenPins, secondBowl);
    }

    private void validSecondFallenPins(int fallenPins) {
        if (fallenPins > MAX_COUNT_OF_PINS || this.fallenPins + fallenPins > MAX_COUNT_OF_PINS) {
            throw new InvalidScoreException("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
        }
    }
}
