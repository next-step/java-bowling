package bowling.score;

public class Score {

    private static final int ZERO_WAIT_LEFTOVER = 0;
    private static final int ONE_WAIT_LEFTOVER = 1;
    private static final int TWO_WAIT_LEFTOVER = 2;

    private static final int MAX_SCORE = 10;

    private int score;
    private int leftover;

    private Score(int score, int leftover) {
        this.score = score;
        this.leftover = leftover;
    }

    public static Score toMiss(int score) {
        return new Score(score, ZERO_WAIT_LEFTOVER);
    }

    public static Score toSpare() {
        return new Score(MAX_SCORE, ONE_WAIT_LEFTOVER);
    }

    public static Score toStrike() {
        return new Score(MAX_SCORE, TWO_WAIT_LEFTOVER);
    }

    public void bonusScore(int score) {
        if (!canCalculate()) {
            throw new IllegalStateException("본 점수는 추가 점수가 없는 Score 입니다.");
        }
        this.score += score;
        this.leftover--;
    }

    public boolean canCalculate() {
        return leftover > ZERO_WAIT_LEFTOVER;
    }

    public int score() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", leftover=" + leftover +
                '}';
    }
}
