package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class FrameScore {
    public static final int SCORE_COUNT = 2;

    private final List<Score> scores;

    private FrameScore() {
        this.scores = new ArrayList<>();
    }

    public static FrameScore create() {
        return new FrameScore();
    }

    public void add(Score newScore) {
        validate(newScore);
        scores.add(newScore);

        if (sum().equals(Score.MAX_SCORE) && scores.size() < SCORE_COUNT) {
            initializeLeftScoreToZero();
        }
    }

    private void validate(Score newScore) {
        validateNotExceedMaxScore(newScore);
        validateScoreCount();
    }

    private void validateScoreCount() {
        if (scores.size() >= SCORE_COUNT) {
            throw new IllegalStateException("더 이상 점수를 입력할 수 없습니다.");
        }
    }

    private void validateNotExceedMaxScore(Score newScore) {
        if (isExceedMaxScoreWith(newScore)) {
            throw new IllegalArgumentException("점수의 합이 최대값을 초과하였습니다.");
        }
    }

    private boolean isExceedMaxScoreWith(Score newScore) {
        Score newSum = sum().add(newScore);
        return newSum.isGreaterThan(Score.MAX_SCORE);
    }

    public Score sum() {
        return scores.stream()
                .reduce(Score.ZERO, Score::add);
    }

    private void initializeLeftScoreToZero() {
        int leftScoreCount = SCORE_COUNT - scores.size();

        for (int i = 0; i < leftScoreCount; i++) {
            scores.add(Score.ZERO);
        }
    }

    public boolean canAddMoreScore() {
        return scores.size() < SCORE_COUNT;
    }

    public boolean canCheckResult() {
        return scores.size() == SCORE_COUNT;
    }

    public Result checkResult() {
        if (!canCheckResult()) {
            throw new IllegalStateException("결과를 확인할 수 없습니다.");
        }

        return Result.findByFrameScore(getFirst(), getSecond());
    }

    public Score getFirst() {
        return scores.size() < 1 ? Score.ZERO : scores.get(0);
    }

    public Score getSecond() {
        return scores.size() < 2 ? Score.ZERO : scores.get(1);
    }

    public int size() {
        return scores.size();
    }
}
