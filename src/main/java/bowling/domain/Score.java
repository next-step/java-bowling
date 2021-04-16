package bowling.domain;

public class Score {

    private int score;

    public void plusScore(int pins) {
        score += pins;
    }

    public int totalScore() {
        return score;
    }
}
