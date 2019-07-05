package domain;

public class NormalFrame {
    protected static final int NORMAL_FRAME_SIZE = 2;
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
