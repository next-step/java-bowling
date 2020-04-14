package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NomalFrame implements Frame {
    private static final int TRYABLE_COUNT = 2;
    private static final int MAX_POINT_COUNT = 10;
    private static final int STRIKE_POINT = 10;

    // 볼링 칠때마다 쌓인다
    private List<Point> points;

    public NomalFrame() {
        points = new ArrayList<>();
    }

    @Override
    public void throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            this.points.add(point);
            return;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    @Override
    public boolean isThrowable() {
        // 두번 다 던졌을때
        if (points.size() >= TRYABLE_COUNT) {
            return false;
        }
        // 초구 스트라이크 일때
        if (isStrike()) {
            return false;
        }
        return true;
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
        if (points.size() == 1 && getSumPoints() == STRIKE_POINT) {
            return true;
        }
        return false;
    }
}
