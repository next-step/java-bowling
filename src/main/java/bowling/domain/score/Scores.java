package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.score.Score.MAX_SCORE;

public class Scores {
    private static final int MAX_TRY_COUNT = 2;
    private static final int MAX_TRY_COUNT_AT_LAST = 3;
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
        return scores;
    }

    public int sum() {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public Score getScore(int tryCount) {
        return scores.get(tryCount - 1);
    }

    public Scores add(int score, boolean isLast) {
        List<Score> scores = new ArrayList<>(this.scores);
        scores.add(makeScore(score, isLast));
        return new Scores(scores);
    }

    private boolean canNotBeSpare(boolean isLast) {
        return isFirstTry() || (isLast && isSecondTry() && !isFirstStrike());
    }

    private boolean isSecondTry() {
        return tryCount == MAX_TRY_COUNT;
    }

    private boolean isFirstTry() {
        return tryCount == 0;
    }

    private Score makeScore(int score, boolean isLast) {
        if (canNotBeSpare(isLast)) {
            return Score.of(score, false);
        }
        return Score.of(score, isSpare(score));
    }

    private boolean isSpare(int score) {
        return getScore(tryCount).getScore() + score == MAX_SCORE;
    }

    public boolean isFinished(boolean isLast) {
        if (isLast) {
            return isFinishedAtLast();
        }
        return isFinished();
    }

    private boolean isFinishedAtLast() {
        return (isSecondTry() && sum() < MAX_SCORE) || isThirdTry();
    }

    private boolean isThirdTry() {
        return tryCount == MAX_TRY_COUNT_AT_LAST;
    }

    private boolean isFinished() {
        return (isFirstStrike()) || isSecondTry();
    }

    private boolean isFirstStrike() {
        return tryCount >= 1 && getScore(1).isStrike();
    }

    private boolean isSecondSpare() {
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
        return !isLast && isFinished() && (isFirstStrike() || isSecondSpare());
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
        if (isFirstStrike()) {
            return 2;
        }
        if (isSecondSpare()) {
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
