package domain;

public class NormalFrame {
    private Score score;

    public NormalFrame() {
        this.score = new Score();
    }

    public int doBowling(int score) {
        return this.score.bowl(score);
    }

    public String getScore() {
        return score.getScore();
    }
}
