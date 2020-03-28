package bowling.domain.frame;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int fallenPins) {
        return new Score(this.score += fallenPins, this.left - 1);
    }

    public int getScore() {
        if (!isCalculation()) {
            throw new IllegalArgumentException();
        }
        return this.score;
    }

    public boolean isCalculation() {
        return left == 0;
    }
}
