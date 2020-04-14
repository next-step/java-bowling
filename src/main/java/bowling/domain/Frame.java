package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int TRYABLE_COUNT = 2;
    private static final int MAX_POINT_COUNT = 10;

    private List<Point> points;

    public Frame() {
        points = new ArrayList<>();
    }

    public boolean throwBall(int fallenCount) {
        if (isThrowable() && (getLeftPin() >= fallenCount)) {
            this.points.add(new Point(fallenCount));
            return true;
        }
        return false;
    }

    public boolean isThrowable() {
        // 두번 다 던졌을때
        if (points.size() >= TRYABLE_COUNT) {
            return false;
        }
        // 초구 스트라이크 일때
        if (points.size() == 1 && getSumPoints() == MAX_POINT_COUNT) {
            return false;
        }
        return true;
    }

    public int getLeftPin() {
        return MAX_POINT_COUNT - getSumPoints();
    }

    private int getSumPoints() {
        return this.points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }
}
