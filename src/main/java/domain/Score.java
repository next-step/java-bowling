package domain;

public class Score {
    private static final int NO_LEFT = 0;
    public static final int ONCE_LEFT = 1;
    public static final int TWICE_LEFT = 2;
    public static final int TEN = 10;

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
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

    public int getScore() {
        return score;
    }

    public int getLeft() {
        return left;
    }
}
