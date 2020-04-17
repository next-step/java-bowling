package bowling.domain;

import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {
    private static final int TRYABLE_COUNT = 2;
    private static final int MAX_POINT_COUNT = 10;
    private static final int STRIKE_POINT = 10;

    // 볼링 칠때마다 쌓인다
    private List<Point> points;
    private final int no;

    public NormalFrame(int no) {
        this.no = no;
        points = new ArrayList<>();
    }

    @Override
    public Frame throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            this.points.add(point);
            return this;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    @Override
    public boolean isThrowable() {
        // 두번 다 던졌을때 or 초구 스트라이크 일때
        if (points.size() >= TRYABLE_COUNT || isStrike()) {
            return false;
        }
        return true;
    }

    @Override
    public int getFrameNo() {
        return this.no;
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }

    @Override
    public String getScoreMark() {
        return Score.getScoreMark(this);
    }

    // 남은 핀
    private int getLeftPin() {
        return MAX_POINT_COUNT - getSumPoints();
    }

    private int getSumPoints() {
        return this.points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    private boolean isStrike() {
        if (points.size() == 1 && points.get(0).getPoint() == STRIKE_POINT) {
            return true;
        }
        return false;
    }
}
