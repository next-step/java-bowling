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
    public static final int FRAME_MAX_SCORE = 10;
    public static final int FRAME_MAX_BOWL_COUNT = 2;
    public static final int STRIKE_BOWL_COUNT = 1;
    private List<Pin> downPins;

    NormalFrameScore() {
        this.downPins = new ArrayList<>();
    }

    boolean addBowlScore(int downCount) {
        if (invalidScore(downCount) || invalidBowlCount()) {
            return false;
        }
        downPins.add(Pin.fallDown(downCount));
        return true;
    }

    boolean isStrike() {
        return countBowl() == STRIKE_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    boolean isSpare() {
        return countBowl() == FRAME_MAX_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    int sum() {
        return downPins.stream()
                .mapToInt(Pin::fallCount)
                .sum();
    }

    boolean invalidBowlCount() {
        return countBowl() >= FRAME_MAX_BOWL_COUNT;
    }

    int countBowl() {
        return downPins.size();
    }

    boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }
}
