package bowling.domain;

import java.util.Objects;

class Score {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    static Score of(int score) {
        if (score < MIN || MAX < score) {
            throw new IllegalArgumentException(String.format("create ShotScore fail, score must be %d~%d : score = %d", MIN, MAX, score));
        }

        return new Score(score);
    }

    boolean isMax() {
        return isMax(0);
    }

    boolean isMin() {
        return this.score == MIN;
    }

    boolean isMax(int addScore) {
        int totalScore = this.score + addScore;
        if (MAX < totalScore) {
            throw new IllegalArgumentException(String.format("check max fail, score + addScore must be %d~%d : thisScore = %d, addScore = %d", MIN, MAX, this.score, addScore));
        }
        return totalScore == MAX;
    }

    int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score{" +
                score +
                '}';
    }
}
