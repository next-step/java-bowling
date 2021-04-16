package bowling.service;

public class BowlingService {

    private int score;

    public void pitch(int pitch) {
        this.score += pitch;
    }

    public int totalScore() {
        return score;
    }
}
