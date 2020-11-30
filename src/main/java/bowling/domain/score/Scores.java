package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    protected final List<Score> scores;

    protected Scores(List<Score> scores) {
        this.scores = scores;
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
        return sum(this.scores);
    }

    private int sum(List<Score> scores) {
        return scores.stream()
                .mapToInt(Score::getScore)
                .sum();
    }

    public Scores add(Score score) {
        List<Score> scores = new ArrayList<>(this.scores);
        scores.add(score);
        return new Scores(scores);
    }

    private boolean hasFirstStrike() {
        return scores.get(0).isStrike();
    }

    private boolean hasSecondSpare() {
        return scores.get(1).isSpare();
    }

    public Integer calculate(Integer previousScore, List<Score> nextScores) {
        if (needNextScores()) {
            return calculateWithNext(previousScore, nextScores);
        }
        return calculate(previousScore);
    }

    protected boolean needNextScores() {
        return hasFirstStrike() || hasSecondSpare();
    }

    private Integer calculateWithNext(Integer previousScore, List<Score> nextScores) {
        if (hasEnoughNextScores(nextScores)) {
            return calculate(previousScore) + sumNextScores(nextScores);
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
        return sum(nextScores.subList(0, getMinimumTryCount()));
    }

    public int calculate(Integer previousScore) {
        return previousScore + sum();
    }
}
