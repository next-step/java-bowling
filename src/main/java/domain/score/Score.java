package domain.score;

import java.util.Objects;

import static domain.pin.Pin.MAXIMUM_PINS;

public class Score {
    private final int score; // 현재까지 점수
    private final int left; // 남은 시도 횟수

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int countOfPins, int left) {
        return new Score(countOfPins, left);
    }

    public static Score of(int countOfPins) {
        return Score.of(countOfPins, 0);
    }

    public static Score ofStrike() {
        return Score.of(MAXIMUM_PINS, 2);
    }

    public static Score ofSpare() {
        return Score.of(MAXIMUM_PINS, 1);
    }

    public int getScore() {
        return score;
    }

    public int getLeft() {
        return left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score + countOfPins, left - 1);
    }

    public int getCalculatedScore() {
        if (!isScoreCalculationFinished()) {
            throw new CannotCalculateException();
        }
        return score;
    }

    public boolean isScoreCalculationFinished() {
        return left == 0;
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