package bowling.domain.point;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private static final int MAX_POINT_COUNT = 10;
    private static final int STRIKE_POINT = 10;
    private static final int DOUBLE_STRIKE_POINT = 20;
    private static final int SPARE_POINT = 10;

    private List<Point> points;

    public Points() {
        this.points = new ArrayList<>();
    }

    public Points(List<Point> points) {
        this.points = points;
    }

    public Points addPoint(Point point) {
        points.add(point);
        return new Points(points);
    }

    public int getTryCount() {
        return points.size();
    }

    public int getPoint(int index) {
        return points.get(index).getPoint();
    }

    public Point get(int index) {
        return new Point(points.get(index).getPoint());
    }

    public int getSum() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public boolean isFirstStrike() {
        if (points.size() == 1 && getPoint(0) == STRIKE_POINT) {
            return true;
        }
        return false;
    }

    public boolean isDoubleStrike() {
        if (points.size() == 2 && getSum() == DOUBLE_STRIKE_POINT) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (points.size() == 2 && getSum() == SPARE_POINT) {
            return true;
        }
        return false;
    }

    public int getLeftPoint() {
        if (points.size() == 1 && !isFirstStrike()) {
            return MAX_POINT_COUNT - getSum();
        }
        return MAX_POINT_COUNT;
    }
}
