package bowling.domain;

import bowling.score.Score;

public class FinalFrame implements Frame {
    private static final int MAX_POINT_COUNT = 10;

    // 볼링 칠때마다 쌓인다
    private Points points;
    private final int frameNo;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        points = new Points();
    }

    @Override
    public Frame throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            this.points.addPoint(point);
            return this;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    @Override
    public boolean isThrowable() {
        // 0번 또는 1번 던졌을때
        if (points.size() == 0 || points.size() == 1) {
            return true;
        }
        // 스트라이크 또는 스페어 처리됬을때
        if (points.isSpare() || points.isDoubleStrike()) {
            return true;
        }
        return false;
    }

    @Override
    public int getFrameNo() {
        return this.frameNo;
    }

    @Override
    public Points getPoints() {
        return this.points;
    }

    @Override
    public String getScoreMark() {
        return Score.getScoreMark(this);
    }

    // 남은 핀
    private int getLeftPin() {
        if (points.isStrike()) {
            return MAX_POINT_COUNT;
        }
        if (points.size() == 1) {
            return MAX_POINT_COUNT - this.points.getSum();
        }
        return MAX_POINT_COUNT;
    }
}
