package bowling.domain;

public class Score {
    private int score;
    private final int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score play(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public int getScore() {
        if (!canCalucateScore()) {
            throw new IllegalArgumentException("cant calculate score");
        }
        return this.score;
    }

    public boolean canCalucateScore() {
        return left == 0;
    }

    public int getLeft() {
        return left;
    }

}
