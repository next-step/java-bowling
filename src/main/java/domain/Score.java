package domain;

public class Score {

    private int score;

    private Score(int fallenPins) {
        this.score = fallenPins;
    }

    public static Score from(int fallenPins) {
        return new Score(fallenPins);
    }

    public boolean isSame(int fallenPins) {
        return score == fallenPins;
    }
}
