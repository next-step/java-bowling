package bowling.domain;

import bowling.score.Score;

public class NormalFrame implements Frame {
    private static final int TRYABLE_COUNT = 2;
    private static final int MAX_POINT_COUNT = 10;
    private static final int STRIKE_POINT = 10;

    // 볼링 칠때마다 쌓인다
    private Points points;
    private final int no;

    public NormalFrame(int no) {
        this.no = no;
        points = new Points();
    }

    @Override
    public Frame throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            points.addPoint(point);
            return this;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    @Override
    public boolean isThrowable() {
        // 두번 다 던졌을때 or 초구 스트라이크 일때
        if (points.size() >= TRYABLE_COUNT || points.isStrike()) {
            return false;
        }
        return true;
    }

    @Override
    public int getFrameNo() {
        return this.no;
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
        return MAX_POINT_COUNT - this.points.getSum();
    }
}
