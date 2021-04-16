package bowling.service;

public class BowlingService {

    private int score;

    public void pitch(int pins) {
        this.score += pins;
    }

    public int totalScore() {
        return score;
    }
}
