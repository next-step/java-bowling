package bowling.domain;

public class NormalFrame {
    private static final int FIRST_FRAME = 1;
    private int score;
    private int index;

    private NormalFrame(final int index) {
        this.score = 0;
        this.index = index;
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    public NormalFrame nextFrame() {
        return new NormalFrame(index + 1);
    }

    public void sumScore(int score) {
        this.score += score;
    }

    public int getIndex() {
        return index;
    }

    public int getScore() {
        return score;
    }
}
