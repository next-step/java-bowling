package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
class FrameScore {
    private static final int LIMIT_SCORE_COUNT = 2;
    private static final int MAX_SUM_SCORE = 10;
    private List<Score> scores;

    FrameScore() {
        this.scores = new ArrayList<>();
    }

    FrameScore(int score) {
        List<Score> scores = new ArrayList<>();
        scores.add(Score.of(score));
        this.scores = scores;
    }

    FrameScore(List<Score> score) {
        this.scores = new ArrayList<>(score);
    }

    boolean addScore(int fallCount) {
        if (isExitFrame() || isStrike()) {
            return false;
        }

        if (isOverScore(fallCount)) {
            throw new IllegalArgumentException("두번 투구의 합은 10을 넘을 수 없습니다.");
        }

        scores.add(Score.of(fallCount));
        return true;
    }

    int bowlCount() {
        return scores.size();
    }

    boolean isStrike() {
        return scores.stream()
                .filter(Score::isStrike)
                .findFirst()
                .isPresent();
    }

    boolean isOverScore(int fallCount) {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum() + fallCount > MAX_SUM_SCORE;
    }

    boolean isExitFrame() {
        return scores.size() >= LIMIT_SCORE_COUNT;
    }

    List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    List<Integer> getScoreNumber() {
        List<Integer> scoreIncrease = new ArrayList<>();
        int sum = 0;
        for (Score score : scores) {
            sum += score.getScore();
            scoreIncrease.add(sum);
        }
        return scoreIncrease;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }

    @Override
    public String toString() {
        return "FrameScore{" +
                "scores=" + scores +
                '}';
    }
}
