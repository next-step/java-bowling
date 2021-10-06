package step4.domain;

import step4.exception.minimumLeftExcpetion;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
        checkValidLeft(left);
    }

    public void throwBowl(int falledPins) {
        this.score += falledPins;
        this.left --;
        checkValidLeft(left);
    }

    private void checkValidLeft(int left) {
        if (left < 0) {
            throw new minimumLeftExcpetion();
        }
    }

    public String getScore() {
        return Integer.toString(score);
    }
}
