package bowling.domain.score.scores;

import bowling.domain.score.Score;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Scores {
    protected static final int SCORE_STRIKE = 10;

    protected final List<Score> scores;

    public abstract void validateScore();

    public abstract boolean isNotEndScore(Scores scores);

    public abstract boolean isChanceMinusTwo();

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public Score first() {
        return this.scores.get(0);
    }

    public Score second() {
        return this.scores.get(1);
    }

    public Score third() {
        return this.scores.get(2);
    }

    public boolean isSizeEqual(int size) {
        return this.scores.size() == size;
    }

    public boolean isSizeOver(int size) {
        return this.scores.size() > size;
    }

    public int sum() {
        return this.scores.stream()
                .mapToInt(Score::value)
                .sum();
    }

    public static int sumScores(Score... scores) {
        return Arrays.stream(scores)
                .mapToInt(Score::value)
                .sum();
    }

    public boolean isSpare() {
        return this.isSizeOver(1) && Scores.sumScores(this.first(), this.second()) == SCORE_STRIKE;
    }

    protected boolean isStrike() {
        return this.first().isStrike();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores1 = (Scores) o;
        return Objects.equals(this.scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
