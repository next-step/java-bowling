package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.domain.score.Score.MAX_SCORE;

public class Scores {
    private static final int MAX_TRY_COUNT = 2;
    private static final int MAX_TRY_COUNT_AT_LAST = 3;
    private static final int MAX_SCORES = 10;
    private static final int MAX_SCORES_AT_LAST = 30;
    private final List<Score> scores;
    private final int tryCount;

    private Scores(List<Score> scores) {
        this.scores = scores;
        this.tryCount = scores.size();
    }

    public static Scores empty() {
        return new Scores(new ArrayList<>());
    }

    public static Scores of(List<Score> scores) {
        return new Scores(scores);
    }

    public List<Score> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public Score getScore(int tryCount) {
        if (tryCount > this.tryCount || tryCount < 1) {
            return null;
        }
        return scores.get(tryCount - 1);
    }

    public Scores add(int score, boolean isLast) {
        validateMaxTryCount(isLast);
        validateMaxScores(score, isLast);
        return add(score);
    }

    private Scores add(int score) {
        List<Score> scores = new ArrayList<>(this.scores);
        scores.add(Score.of(getScore(tryCount), score));
        return new Scores(scores);
    }

    private void validateMaxTryCount(boolean isLast) {
        if (isFinished(isLast)) {
            throw new InvalidScoreAddException();
        }
    }

    private void validateMaxScores(int score, boolean isLast) {
        if (sum() + score > getMaxScores(isLast)) {
            throw new InvalidMaxScoresException();
        }
    }

    private int getMaxScores(boolean isLast) {
        if (isLast) {
            return MAX_SCORES_AT_LAST;
        }
        return MAX_SCORES;
    }

    public boolean isFinished(boolean isLast) {
        if (isLast) {
            return isFinishedAtLast();
        }
        return isFinished();
    }

    private boolean isFinishedAtLast() {
        return (secondTried() && sum() < MAX_SCORE) || thirdTried();
    }

    private boolean secondTried() {
        return tryCount == MAX_TRY_COUNT;
    }

    private boolean thirdTried() {
        return tryCount == MAX_TRY_COUNT_AT_LAST;
    }

    private boolean isFinished() {
        return (hasFirstStrike()) || secondTried();
    }

    private boolean hasFirstStrike() {
        return tryCount >= 1 && getScore(1).isStrike();
    }

    private boolean hasSecondSpare() {
        return tryCount >= 2 && getScore(2).isSpare();
    }

    public Integer calculateScore(Integer previousScore, List<Score> nextScores, boolean isLast) {
        if (!isFinished(isLast)) {
            return null;
        }
        if (needNextScores(isLast)) {
            return calculateScoreWithNext(previousScore, nextScores);
        }
        return calculateWithoutNext(previousScore);
    }

    private boolean needNextScores(boolean isLast) {
        return !isLast && isFinished() && (hasFirstStrike() || hasSecondSpare());
    }

    private Integer calculateScoreWithNext(Integer previousScore, List<Score> nextScores) {
        if (hasEnoughNextScores(nextScores)) {
            return calculateWithoutNext(previousScore) + sumNextScores(nextScores);
        }
        return null;
    }

    private boolean hasEnoughNextScores(List<Score> nextScores) {
        return getMinimumTryCount() <= nextScores.size();
    }

    private int getMinimumTryCount() {
        if (hasFirstStrike()) {
            return 2;
        }
        if (hasSecondSpare()) {
            return 1;
        }
        return 0;
    }

    private Integer sumNextScores(List<Score> nextScores) {
        return nextScores.subList(0, getMinimumTryCount())
                .stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    private int calculateWithoutNext(Integer previousScore) {
        return previousScore + sum();
    }
}
