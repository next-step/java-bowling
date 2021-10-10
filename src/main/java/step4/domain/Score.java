package step4.domain;

import step4.exception.MinimumTurnExcpetion;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
        checkValidLeft(left);
    }

    public Score throwBowl(int falledPins) {
        checkValidLeft(left);
        return new Score(this.score + falledPins, left - 1);

    }

    private void checkValidLeft(int left) {
        if (left < 0) {
            throw new MinimumTurnExcpetion();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public boolean canCalculate() {
        return left == 0;
    }
}
