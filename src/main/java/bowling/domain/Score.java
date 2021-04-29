package bowling.domain;

public class Score {

    private static final int NONE_OPPORTUNITY = 0;
    private static final int MAX_SCORE = 10;
    private static final int SPARE_OPPORTUNITY = 1;
    private static final int STRIKE_OPPORTUNITY = 2;

    private int score;
    private int leftOpportunity;

    private Score(int score, int leftOpportunity) {
        this.score = score;
        this.leftOpportunity = leftOpportunity;
    }

    public static Score ofMiss(int score) {
        return new Score(score, NONE_OPPORTUNITY);
    }

    public static Score ofGutter(int score) {
        return new Score(score, NONE_OPPORTUNITY);
    }

    public static Score ofSpare() {
        return new Score(MAX_SCORE, SPARE_OPPORTUNITY);
    }

    public static Score ofStrike() {
        return new Score(MAX_SCORE, STRIKE_OPPORTUNITY);
    }

    public int calculateScore() {
        return -2;
    }

    public void spreadScore(Score beforeScore) {

    }

    public void throwBall(int score) {
        this.score += score;
        this.leftOpportunity -= 1;
    }
}
