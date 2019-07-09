package bowling.domain;

import java.util.List;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-09 12:41
 */
public class Frame {
    private static final int LIMIT_FREAME_COUNT = 2;
    private final List<Pin> pins;

    public Frame(List<Pin> pins) {
        this.pins = pins;
    }

    public int checkRemainPin() {
        return Pin.getRemainPinCount(pins.stream()
                .mapToInt(pin -> pin.getFallPinCount())
                .sum());
    }

    public boolean isFinishFrame() {
        return pins.size() == LIMIT_FREAME_COUNT;
    }
}
