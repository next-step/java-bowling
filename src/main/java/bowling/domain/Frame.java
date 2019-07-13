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
 * create date  : 2019-07-13 17:16
 */
public class Frame {
    private static int AUTO_INCREASE = 1;

    private List<Pin> pins;
    private final int number;

    public Frame() {
        this.pins = new ArrayList<>();
        this.number = AUTO_INCREASE++;
    }

    public int bowl(int downCount) {
        pins.add(Pin.fallDown(downCount));
        return sum();
    }

    public int countBowl() {
        return pins.size();
    }

    public int sum() {
        return pins.stream()
                .mapToInt(Pin::fallCount)
                .sum();
    }

    public int getNumber() {
        return number;
    }
}
