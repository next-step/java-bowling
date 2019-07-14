package domain;

import View.OutView;

public class Score {
    private static final int NO_LEFT = 0;
    private static final int ONCE_LEFT = 1;
    private static final int TWICE_LEFT = 2;
    private static final int TEN = 10;

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            //throw new CannotCalculateException();
            return -1; // 리팩토링 하기
        }
        return score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public static Score ofMiss(int score) {
        return new Score(score, NO_LEFT);
    }

    public static Score ofSpare() {
        return new Score(TEN, ONCE_LEFT);
    }

    public static Score ofStrike() {
        return new Score(TEN, TWICE_LEFT);
    }

    public int getLeft() {
        return left;
    }
}
