package bowling.model;

public class Score {
    private int score;
    private int leftCount;

    private Score(int score, int leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public static Score miss(int score) {
        return new Score(score, 0);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score init() {
        return new Score(0, 2);
    }

    public static Score of(int score, int leftCount) {
        return new Score(score, leftCount);
    }

    public boolean canCalculate() {
        return leftCount == 0;
    }

    public Score add(int score) {
        return new Score(this.score + score, leftCount - 1);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
