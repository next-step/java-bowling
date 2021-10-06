package step4.domain;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public void throwBowl(int falledPins) {
        this.score += falledPins;
        this.left ++;
    }
}
