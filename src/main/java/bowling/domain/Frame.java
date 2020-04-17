package bowling.domain;

import bowling.score.Score;

public abstract class Frame {
    protected static final int MAX_POINT_COUNT = 10;

    protected Points points;
    protected int frameNo;

    public Frame(int frameNo) {
        this.frameNo = frameNo;
        points = new Points();
    }

    abstract boolean isThrowable();

    public Frame throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            this.points.addPoint(point);
            return this;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    public int getFrameNo() {
        return this.frameNo;
    }

    public Points getPoints() {
        return this.points;
    }

    public String getScoreMark() {
        return Score.getScoreMark(this);
    }

    public int getLeftPin() {
        if (points.getTryCount() == 1 && !points.isFirstStrike()) {
            return MAX_POINT_COUNT - this.points.getSum();
        }
        return MAX_POINT_COUNT;
    }
}
