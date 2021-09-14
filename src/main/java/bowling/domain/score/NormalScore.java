package bowling.domain.score;

import java.util.Objects;

public class NormalScore extends Score {

    private final int first;

    private final int second;

    private final boolean isDone;

    private NormalScore(int score, boolean isDone) {
        this.first = score;
        this.second = 0;
        this.isDone = isDone;
    }

    private NormalScore(int first, int second, boolean isDone) {
        this.first = first;
        this.second = second;
        this.isDone = isDone;
    }

    public static NormalScore start() {
        return new NormalScore(0, 0, false);
    }

    public static NormalScore first(int score) {
        validateScore(score);
        return new NormalScore(score, false);
    }

    public static NormalScore none() {
        return new NormalScore(0, 0, true);
    }

    public static boolean isNone(NormalScore score) {
        return none().equals(score);
    }

    public boolean isStrike() {
        return first == MAX_SCORE;
    }

    public NormalScore second(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new NormalScore(this.first, score, true);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isSpare() {
        return !isStrike() && first + second == MAX_SCORE;
    }

    public boolean isFirstTryNoPoint() {
        return first == 0;
    }

    public boolean isSecondTryNoPoint() {
        return second == 0;
    }

    private void validateCombinedScores(int score) {
        if (this.first + score > MAX_SCORE) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return first == that.first && second == that.second && isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, isDone);
    }
}
