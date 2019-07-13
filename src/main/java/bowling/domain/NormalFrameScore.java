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
    public static final String INVALID_DOWN_BOWL_COUNT = "넘어진 핀의 개수가 유효하지 않습니다. [%d]";
    public static final String INVALID_BOWL_COUNT = "이미 2번의 투구를 끝냈습니다. 다음 Frame에 투구하세요";
    public static final int FRAME_MAX_SCORE = 10;
    public static final int FRAME_MAX_BOWL_COUNT = 2;
    public static final int STRIKE_BOWL_COUNT = 1;
    private List<Pin> downPins;

    public NormalFrameScore() {
        this.downPins = new ArrayList<>();
    }

    public void addBowlScore(int downCount) {
        if (invalidScore(downCount)) {
            throw new IllegalArgumentException(String.format(INVALID_DOWN_BOWL_COUNT, downCount));
        }
        if (invalidBowlCount()) {
            throw new IllegalArgumentException(INVALID_BOWL_COUNT);
        }
        downPins.add(Pin.fallDown(downCount));
    }

    public boolean isStrike() {
        return countBowl() == STRIKE_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    public boolean isSpare() {
        return countBowl() == FRAME_MAX_BOWL_COUNT && sum() == FRAME_MAX_SCORE;
    }

    public int sum() {
        return downPins.stream()
                .mapToInt(Pin::fallCount)
                .sum();
    }

    private boolean invalidBowlCount() {
        return countBowl() >= FRAME_MAX_BOWL_COUNT;
    }

    private int countBowl() {
        return downPins.size();
    }

    private boolean invalidScore(int downCount) {
        return sum() + downCount > FRAME_MAX_SCORE;
    }
}
