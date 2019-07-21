package domain;

public class Score {

    private int score;
    private int remainingAddition;

    private Score(int score, int remainingAddition) {
        this.score = score;
        this.remainingAddition = remainingAddition;
    }

    public static Score of(int score, int remainingAddition) {
        return new Score(score, remainingAddition);
    }

    public int getScore() {
        if (remainingAddition != 0) {
            throw new UndoneCalculationException();
        }
        return score;
    }
}
