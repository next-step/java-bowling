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
 * create date  : 2019-07-16 23:22
 */
public class FrameScore {
    public static final int FRAME_MAX_SCORE = 10;
    public static final int FRAME_MAX_BOWL_COUNT = 2;
    public static final int STRIKE_BOWL_COUNT = 1;
    public static final int SPARE_BOWL_COUNT = 2;

    private List<Point> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public boolean addPoint(Point fallCount) {
        if (invalidScore(fallCount)
                || invalidCountBowl()) {
            return false;
        }
        scores.add(fallCount);
        return true;
    }

    public boolean isStrike() {
        return sum() == FRAME_MAX_SCORE && countBowl() == STRIKE_BOWL_COUNT;
    }

    public boolean isSpare() {
        return sum() == FRAME_MAX_SCORE && countBowl() == SPARE_BOWL_COUNT;
    }

    private boolean invalidCountBowl() {
        return countBowl() >= FRAME_MAX_BOWL_COUNT;
    }

    private boolean invalidScore(Point fallCount) {
        return sum() + fallCount.fallCount() > FRAME_MAX_SCORE;
    }

    private int countBowl() {
        return scores.size();
    }

    private int sum() {
        return scores.stream()
                .mapToInt(Point::fallCount)
                .sum();
    }
}
