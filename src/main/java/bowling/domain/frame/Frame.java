package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.PointOutOfRangeException;
import bowling.domain.point.Points;
import bowling.score.Score;

public abstract class Frame {
    private static final int LAST_FRAME_NO = 10;
    protected Points points;
    protected int frameNo;

    public Frame(int frameNo) {
        this.frameNo = frameNo;
        points = new Points();
    }

    abstract public boolean isThrowable();

    public Frame throwBall(int fallenCount) throws PointOutOfRangeException {
        int leftPoint = points.getLeftPoint();
        Point point = Point.of(fallenCount);
        if (point.isScoreable(leftPoint)) {
            this.points.addPoint(point);
            return this;
        }
        throw new OverThrowBallException("다시 입력해주세요(남은 핀: " + leftPoint + ")");
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

    public boolean isLast() {
        if (frameNo == LAST_FRAME_NO && !isThrowable()) {
            return true;
        }
        return false;
    }
}
