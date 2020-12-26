package step2.domain;

import java.util.Objects;

public class Score {

    private static final int ONE_CHANCE = 1;
    public static final int NO_CHANCE = 0;

    private int score;
    private int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int score, int left) {
        return new Score(score, left);
    }

    public Score bowl(int fallingPins) {
        score += fallingPins;
        left -= ONE_CHANCE;
        return new Score(score, left);
    }

    public int getScore() {
        if(validateChance()) {
            throw new IllegalArgumentException("기회가 남았으므로 아직 점수를 계산할 수 없습니다.");
        }
        return score;
    }

    private boolean validateChance() {
        return left != NO_CHANCE;
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
