package bowling;

public class Score {

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
            throw new IllegalStateException("시도 횟수가 남아있어 점수 계산이 불가능합니다.");
        }
        return score;
    }

    public boolean canCalculate() {
        return left == 0;
    }
}
