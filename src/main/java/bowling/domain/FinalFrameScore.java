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
 * create date  : 2019-07-13 22:32
 */
public class FinalFrameScore {
    private static final int FRAME_MAX_SCORE = 30;
    private static final int FRAME_DEFAULT_SCORE = 10;
    private static final int FRAME_DEFAULT_BOWL_COUNT = 2;
    private static final int FIRST_STRIKE_SCORE = 10;
    private static final int ZERO = 0;
    private static final int TWO = 2;

    private List<Pin> downPins;

    FinalFrameScore() {
        this.downPins = new ArrayList<>();
    }


    boolean addBowlScore(int downCount) {
        if (invalidBowlCount() || invalidScore(downCount)) {
            return false;
        }

        downPins.add(Pin.fallDown(downCount));
        return true;
    }

    boolean isFirstBowlStrike() {
        return downPins.get(ZERO).fallCount() == FIRST_STRIKE_SCORE;
    }

    boolean isSpare() {
        return countBowl() == TWO && sum() == FRAME_DEFAULT_SCORE;
    }

    int countBowl() {
        return downPins.size();
    }

    int sum() {
        return downPins.stream()
                .mapToInt(Pin::fallCount)
                .sum();
    }

    boolean invalidBowlCount() {
        if (countBowl() < FRAME_DEFAULT_BOWL_COUNT
                || countBowl() == FRAME_DEFAULT_BOWL_COUNT && isFirstBowlStrike()
                || isSpare()) {
            return false;
        }

        return true;
    }

    boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }
}