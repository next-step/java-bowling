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
    public static final int FRAME_MAX_SCORE = 30;
    public static final int FRAME_DEFAULT_SCORE = 10;
    public static final int ONE = 1;
    public static final int TWO = 2;

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

    boolean isStrike() {
        return sum() == countBowl() * FRAME_DEFAULT_SCORE;
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

    private boolean invalidBowlCount() {
        if (countBowl() == ONE
                || (countBowl() == TWO && (isSpare()) || isStrike())) {
            return false;
        }
        return true;
    }

    private boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }
}