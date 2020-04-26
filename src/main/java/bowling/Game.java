package bowling;

public class Game {
    private int score = 0;
    public Game() {
    }

    public void roll(int pins) {
        score += pins;
    }

    public int getScore() {
        return score;
    }
}
