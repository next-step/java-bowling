package bowling.domain;

public class Score {
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score sum(Score score) {
        return new Score(this.score + score.score);
    }

    public boolean isGutter(){
        return score == 0;
    }

    public boolean isStrike(){
        return score == 10;
    }

    public int getScore() {
        return score;
    }
}
