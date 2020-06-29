package bowling.game;

public class Score {
    private int score;
    private int left;

    public Score(final int score, final int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int basicScore) {
        return new Score(basicScore, 0);
    }

    public Score addBonusScore(int pinCount) {
        if (canCalculateScore()) {
            throw new IllegalStateException("더 이상 보너스 점수를 더할 수 없습니다.");
        }
        return new Score(this.score += pinCount, left - 1);
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public int getScore() {
        return this.score;
    }
}
