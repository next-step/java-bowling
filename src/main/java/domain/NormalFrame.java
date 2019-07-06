package domain;

public class NormalFrame {
    private NormalScore normalScore;

    public NormalFrame() {
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
        return new NormalFrame();
    }

    public String getScore() {
        return normalScore.getScore();
    }

    public int sumScore() {
        return normalScore.sumScore();
    }
}
