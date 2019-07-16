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
    private static final String EXCESS_SCORE_POINT_EXCEPTION_MESSAGE = "입력된 점수는 추가할 수 없습니다.";
    private List<Point> scores;

    public FrameScore() {
        this.scores = new ArrayList<>();
    }

    public void addPoint(Point fallCount) {
        if(sum() + fallCount.fallCount() > FRAME_MAX_SCORE) {
            throw new IllegalArgumentException(EXCESS_SCORE_POINT_EXCEPTION_MESSAGE);
        }
        scores.add(fallCount);
    }

    private int sum() {
        return scores.stream()
                .mapToInt(Point::fallCount)
                .sum();
    }
}
