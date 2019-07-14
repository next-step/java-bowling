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
    private static final int FRAME_MAX_BOWL_COUNT = 3;
    private static final int FRAME_DEFAULT_SCORE = 10;
    private static final int FRAME_DEFAULT_BOWL_COUNT = 2;
    private static final int FIRST_STRIKE_SCORE = 10;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    private List<Pin> downPins;

    FinalFrameScore() {
        this.downPins = new ArrayList<>();
    }


    boolean bowl(int downCount) {
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

    /**
     * 세번째 투구할 수 있는 케이스
     * 1. 첫 번째 투구가 Strike
     * 2. 두 번째 투구가 Spare
     *
     * 그 외에는 2번만 투구 할 수 있다.
     */
    private boolean invalidBowlCount() {
        if (countBowl() < FRAME_DEFAULT_BOWL_COUNT
                || countBowl() == FRAME_DEFAULT_BOWL_COUNT && isFirstBowlStrike()
                || isSpare()) {
            return false;
        }

        return true;
    }

    private boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }
}