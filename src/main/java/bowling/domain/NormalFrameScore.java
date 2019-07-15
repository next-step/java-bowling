package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    private static final int FRAME_MAX_BOWL_COUNT = 2;
    private static final int STRIKE_BOWL_COUNT = 1;
    private static final int LAST_INDEX = 1;

    private List<Pin> downPins;

    NormalFrameScore() {
        this.downPins = new ArrayList<>();
    }

    boolean addBowlScore(int downCount) {
        if (invalidScore(downCount)
                || invalidBowlCount()
                || isStrike()) {
            return false;
        }
        downPins.add(Pin.fallDown(downCount));
        return true;
    }

    boolean isStrike() {
        return countBowl() == STRIKE_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    public boolean isSpare() {
        return countBowl() == FRAME_MAX_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    int sum() {
        return downPins.stream()
                .mapToInt(Pin::downCount)
                .sum();
    }

    boolean invalidBowlCount() {
        return countBowl() >= FRAME_MAX_BOWL_COUNT;
    }

    boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }

    boolean checkBowlPositionFromDownCount(int downCount) {
        return downPins.indexOf(Pin.fallDown(downCount)) == LAST_INDEX;
    }

    int countBowl() {
        return downPins.size();
    }

    Stream<Pin> stream() {
        return downPins.stream();
    }
}
