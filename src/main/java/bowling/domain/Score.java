package bowling.domain;

public class Score {

    private int score;
    private int leftOpportunity;

    private Score(int score, int leftOpportunity) {
        this.score = score;
        this.leftOpportunity = leftOpportunity;
    }

    public static Score of(int score, int leftOpportunity) {
        return new Score(score, leftOpportunity);
    }

    public int calculateScore() {
        return -2;
    }

    public void spreadScore(Score beforeScore) {

    }

    public void throwBall(int score) {
    }
}
