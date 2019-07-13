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
}
