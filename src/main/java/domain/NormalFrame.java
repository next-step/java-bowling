package domain;

public class NormalFrame {
    private NormalScore normalScore;

    public NormalFrame() {
        normalScore = new NormalScore();
    }

    public int doBowling(int point) {
        return normalScore.bowl(point);
    }

    public String getScore() {
        return normalScore.getScore();
    }
}
