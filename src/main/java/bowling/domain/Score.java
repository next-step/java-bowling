package bowling.domain;

public class Score {
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        if (score == 10) {
            return true;
        }
        return false;
    }

    public Score add(Score score) {
        return new Score(this.score + score.getScore());
    }

    public int getScore() {
        return score;
    }

}
