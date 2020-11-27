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
        scores.add(makeScore(score, hasNoSpare(isLast)));
        return new Scores(scores);
    }

    private boolean hasNoSpare(boolean isLast) {
        return tryCount == 0 || (isLast && tryCount == MAX_TRY_COUNT && !isFirstStrike());
    }

    private Score makeScore(int score, boolean hasNoSpare) {
        if (hasNoSpare) {
            return Score.of(score, false);
        }
        return Score.of(score, getPreviousScore() + score == MAX_SCORE);
    }

    private int getPreviousScore() {
        return scores.get(tryCount - 1).getScore();
    }

    public boolean isFinished(boolean isLast) {
        if (isLast) {
            return isFinishedAtLast();
        }
        return isFinished();
    }

    private boolean isFinishedAtLast() {
        return tryCount == MAX_TRY_COUNT && sum() < MAX_SCORE || tryCount == MAX_TRY_COUNT_AT_LAST;
    }

    private boolean isFinished() {
        return (tryCount == 1 && isFirstStrike()) || tryCount == MAX_TRY_COUNT;
    }

    public boolean isFirstStrike() {
        return scores.get(0).isStrike();
    }

    public boolean isSecondSpare() {
        return scores.get(1).isSpare();
    }
}
