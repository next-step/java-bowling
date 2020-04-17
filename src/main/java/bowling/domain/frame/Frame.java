package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.point.PointOutOfRangeException;
import bowling.domain.point.Points;
import bowling.score.Score;

public abstract class Frame {
    protected Points points;
    protected int frameNo;

    public Frame(int frameNo) {
        this.frameNo = frameNo;
        points = new Points();
    }

    abstract boolean isThrowable();

    public Frame throwBall(int fallenCount) throws PointOutOfRangeException {
        int leftPoint = points.getLeftPoint();
        Point point = new Point(fallenCount);
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
}
