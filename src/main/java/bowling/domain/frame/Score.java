package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.exception.CannotScoreCalculateException;

import java.util.Objects;

public class Score {

    public static final int UN_SCORE_STATE = -1;

    private final int score;
    private final int remainingCount;

    private Score(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static Score ofMiss(int totalScore) {
        if (totalScore >= Pins.MAX_RANGE) {
            throw new IllegalArgumentException(String.format("미스 상태의 점수는 %d이상일 수 없습니다.", Pins.MAX_RANGE));
        }
        return new Score(totalScore, 0);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public Score bowl(int count) {
        return new Score(this.score + count, remainingCount - 1);
    }

    public int getScore() {
        if (!isCalculatorScore()) {
            throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
        }
        return this.score;
    }

    public boolean isCalculatorScore() {
        return this.remainingCount == 0;
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
}
