package step2.domain;

import java.util.Objects;

public class Score {

    private static final int STRIKE_CHANCE = 2;
    private static final int CHANCE = 1;
    private static final int NO_CHANCE = 0;

    private int score;
    private int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int score, int left) {
        return new Score(score, left);
    }

    public static Score ofStrike() {
        return new Score(Pitch.MAX_SCORE, STRIKE_CHANCE);
    }

    public static Score ofSpare() {
        return new Score(Pitch.MAX_SCORE, CHANCE);
    }

    public static Score ofMiss(int score) {
        return new Score(score, NO_CHANCE);
    }

    public Score bowl(int fallingPins) {
        score += fallingPins;
        left -= CHANCE;
        return new Score(score, left);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new IllegalArgumentException("아직 점수를 계산할 수 없습니다.");
        }
        return score;
    }

    private boolean canCalculateScore() {
        return left == NO_CHANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
