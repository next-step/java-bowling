package domain;

public class NormalFrame {
    private int frameNumber;
    private NormalScore normalScore;

    public NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.normalScore = new NormalScore();
    }

    public boolean doBowling(int point) {
        if (nowBowlable()) {
            normalScore.bowl(point);
            return true;
        }
        return false;
    }

    public boolean nowBowlable() {
        return normalScore.nowBowlable();
    }

    public NormalFrame nextFrame() {
        return new NormalFrame(frameNumber + 1);
    }

    public String getResult() {
        return normalScore.getResult();
    }

    public int sumScore() {
        return normalScore.sumScore();
    }

    public int getNextFrameNumber() {
        return nowBowlable() ? frameNumber : frameNumber + 1;
    }
}
