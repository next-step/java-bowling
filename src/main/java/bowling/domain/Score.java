package bowling.domain;

public class Score {

    private int score;
    public static final int MAX_SCORE = 10;

    public Score(int score) {
        this.score = score;
    }

    public boolean validateMaxScore() {
        return score == MAX_SCORE;
    }

    public int getScore() {
        return this.score;
    }
}
