package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private final List<Score> scores;
    private final int tryCount;

    public Scores(List<Score> scores) {
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
        return tryCount == 0 || (isLast && tryCount == 2);
    }

    private Score makeScore(int score, boolean hasNoSpare) {
        if (hasNoSpare) {
            return Score.of(score, false);
        }
        return Score.of(score, scores.get(0).getScore() + score == 10);
    }

    public boolean isFinished(boolean isLast) {
        if (isLast) {
            return isFinishedAtLast();
        }
        return isFinished();
    }

    private boolean isFinishedAtLast() {
        return tryCount == 2 && sum() < 10 || tryCount == 3;
    }

    private boolean isFinished() {
        return (tryCount == 1 && scores.get(0).isStrike()) || tryCount == 2;
    }
}
