package bowling.domain;

public class Score {
    private int score;
    private static final int MAX_SCORE = 10;
    public Score(int score) {
        this.score = score;
    }

    protected int score() {
        return score;
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public boolean isSpare(Score s) {
        return this.score + s.score() == MAX_SCORE;
    }
}
