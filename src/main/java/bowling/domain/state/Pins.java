package bowling.domain.state;

import bowling.exception.InvalidScoreException;

public class Pins {
    private static final int MINIMUM_COUNT_OF_PINS = 0;
    private static final int MAXIMUM_COUNT_OF_PINS = 10;

    private int fallenPins;

    Pins(int fallenPins) {
        validPins(fallenPins);
        this.fallenPins = fallenPins;
    }

    private void validPins(int fallenPins) {
        if (fallenPins > MAXIMUM_COUNT_OF_PINS) {
            throw new InvalidScoreException("쓰러진 볼링 핀의 수는 최대 10을 넘을 수 없습니다.");
        }

        if (fallenPins < MINIMUM_COUNT_OF_PINS) {
            throw new InvalidScoreException("쓰러진 볼링 핀의 수는 최소 0 미만일 수 없습니다.");
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }
}
