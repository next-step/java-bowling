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
 * create date  : 2019-07-10 16:12
 */
class FinalFrameScore {
    public static final int LIMIT_SCORE_COUNT = 3;
    private static final int LIMIT_MAX_SCORE = 10;
    private static final int SPARE_SCORE = 10;
    private static final int SPARE_BOWL_COUNT = 2;
    private static final int STRIKE_BOWL_COUNT = 2;
    private static final int MAX_SUM_SCORE = 20;

    private List<Score> scores;

    FinalFrameScore(int score) {
        if (score > LIMIT_MAX_SCORE) {
            throw new IllegalArgumentException("한번에 얻을수 있는 최대 점수를 넘었습니다.");
        }
        List<Score> scores = new ArrayList<>();
        scores.add(Score.of(score));
        this.scores = scores;
    }

    boolean addScore(int fallCount) {
        if (bowlable()) {
            scores.add(Score.of(fallCount));
            return true;
        }
        return false;
    }

    boolean bowlable() {
        if (bowlCount() >= LIMIT_SCORE_COUNT
                || sum() >= MAX_SUM_SCORE
                || (sum() < SPARE_SCORE && bowlCount() >= SPARE_BOWL_COUNT)
                || strikeCount() >= STRIKE_BOWL_COUNT) {
            return false;
        }
        return true;
    }

    int bowlCount() {
        return scores.size();
    }

    int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    int strikeCount() {
        return (int) scores.stream()
                .filter(Score::isStrike)
                .count();
    }

    @Override
    public String toString() {
        return "FinalFrameScore{" +
                "scores=" + scores +
                '}';
    }
}
