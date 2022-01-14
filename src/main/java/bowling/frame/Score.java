package bowling.frame;

import bowling.Pins;
import bowling.exception.CannotScoreCalculateException;

import java.util.Objects;

public class Score {

    public static final int UN_SCORE_STATE = -1;

    private final int score;
    private final int remainingOpportunities;

    public Score(int score, int remainingOpportunities) {
        this.score = score;
        this.remainingOpportunities = remainingOpportunities;
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int totalScore) {
        if (totalScore >= Pins.MAX_PINS_COUNT) {
            throw new IllegalArgumentException(String.format("미스 상태의 점수는 %d이상일 수 없습니다.", Pins.MAX_PINS_COUNT));
        }
        return new Score(totalScore, 0);
    }

    public Score bowl(int fallenPins) {
        if (remainingOpportunities <= 0) {
            throw new CannotScoreCalculateException("기회를 모두 소진 했습니다.");
        }
        return new Score(score + fallenPins, remainingOpportunities - 1);
    }

    public int getScore() throws CannotScoreCalculateException {
        if (!isCalculatorScore()) {
            throw new CannotScoreCalculateException("기회가 남아있어 점수를 확인할 수 없습니다.");
        }
        return score;
    }

    public boolean isCalculatorScore() {
        return remainingOpportunities == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return getScore() == score1.getScore() && remainingOpportunities == score1.remainingOpportunities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore(), remainingOpportunities);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", remainingOpportunities=" + remainingOpportunities +
                '}';
    }
}
