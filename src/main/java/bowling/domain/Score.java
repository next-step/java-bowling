package bowling.domain;

import bowling.domain.pin.Pin;

public class Score {

    private int score;
    private int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofExtra(int score) {
        return new Score(score, 0);
    }

    public int getScore() {
        if (!canGetScore()) {
            throw new IllegalStateException("can't get score");
        }
        return score;
    }

    public boolean canGetScore() {
        return left == 0;
    }

    public Score addedScore(Pin addPin) {
        return new Score(addPin.sum(score), left - 1);
    }
}
