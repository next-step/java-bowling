package bowling.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 14:00
 */
public class NormalFrameScore {
    private static final int MAX_BOWL_COUNT = 2;
    private static final int MAX_DOWN_PIN = 10;
    private static final int STRIKE_BOWL_COUNT = 1;
    private List<Pin> downPin;

    public NormalFrameScore() {
        this.downPin = new ArrayList<>();
    }

    public boolean bowl(int downCount) {
        downPin.add(Pin.fallDown(downCount));
        return false;
    }

    public int bowlCount() {
        return downPin.size();
    }

    public boolean isBowl() {
        if (isStrike()) {
            return false;
        }
        return bowlCount() < MAX_BOWL_COUNT;
    }

    public boolean isStrike() {
        return bowlCount() == STRIKE_BOWL_COUNT && sum() == MAX_DOWN_PIN;
    }

    public int sum() {
        return downPin.stream()
                .mapToInt(pin -> pin.fallCount())
                .sum();
    }
}
