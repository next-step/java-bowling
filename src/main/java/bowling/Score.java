package bowling;

public class Score {

    public static int INCALCULABLE_SCORE = -1;

    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public Score addScore(int falledPins) {
        return new Score(score + falledPins, left - 1);
    }

    public int getScore() {
        if (!canCalculate()) {
            throw new CannotCalculateException();
        }
        return score;
    }

    public boolean canCalculate() {
        return left == 0;
    }
}
