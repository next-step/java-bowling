package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private static final int MAX_TRY_COUNT = 2;
    private static final int MAX_SCORES = 10;
    
    protected final List<Score> scores;
    protected final int tryCount;

    protected Scores(List<Score> scores) {
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

    public Scores add(int score) {
        validateMaxTryCount();
        validateMaxScores(score);
        return addScore(score);
    }

    protected Scores addScore(int score) {
        List<Score> scores = new ArrayList<>(this.scores);
        scores.add(Score.of(getScore(tryCount), score));
        return new Scores(scores);
    }

    private void validateMaxTryCount() {
        if (isFinished()) {
            throw new InvalidScoreAddException();
        }
    }

    private void validateMaxScores(int score) {
        if (sum() + score > getMaxScores()) {
            throw new InvalidMaxScoresException();
        }
    }

    protected int getMaxScores() {
        return MAX_SCORES;
    }

    protected boolean secondTried() {
        return tryCount == MAX_TRY_COUNT;
    }

    public boolean isFinished() {
        return (hasFirstStrike()) || secondTried();
    }

    private boolean hasFirstStrike() {
        return tryCount >= 1 && getScore(1).isStrike();
    }

    private boolean hasSecondSpare() {
        return tryCount >= 2 && getScore(2).isSpare();
    }

    public Integer calculateScore(Integer previousScore, List<Score> nextScores) {
        if (!isFinished()) {
            return null;
        }
        if (needNextScores()) {
            return calculateScoreWithNext(previousScore, nextScores);
        }
        return calculateWithoutNext(previousScore);
    }

    protected boolean needNextScores() {
        return hasFirstStrike() || hasSecondSpare();
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
