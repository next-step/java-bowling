package domain;

public class NormalFrame {
    private Score score;

    public NormalFrame() {
        this.score = new Score();
    }

    public int doBowling(int point) {
        return this.score.bowl(point);
    }

    public String getScore() {
        return score.getScore();
    }
}
