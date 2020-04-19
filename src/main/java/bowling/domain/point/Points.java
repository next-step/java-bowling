package bowling.domain.point;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private static final int FIRST_POINT_INDEX = 0;
    private static final int SECOND_POINT_INDEX = 1;
    private static final int THIRD_POINT_INDEX = 2;

    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;

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

    public Point get(int index) {
        return points.get(index);
    }

    public int getSum() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public boolean isFirstStrike() {
        if (points.size() == FIRST_TRY_COUNT && getSum() == STRIKE_POINT) {
            return true;
        }
        return false;
    }

    public boolean isDoubleStrike() {
        if (points.size() == SECOND_TRY_COUNT && getSum() == DOUBLE_STRIKE_POINT) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (points.size() == SECOND_TRY_COUNT && getSum() == SPARE_POINT && !isFirstStrike()) {
            return true;
        }
        return false;
    }

    public int getLeftPoint() {
        if (points.size() == FIRST_TRY_COUNT && !isFirstStrike()) {
            return MAX_POINT_COUNT - getSum();
        }
        return MAX_POINT_COUNT;
    }

    public Point getFirstPoint(){
        return points.get(FIRST_POINT_INDEX);
    }

    public Point getSecondPoint(){
        return points.get(SECOND_POINT_INDEX);
    }

    public Point getThirdPoint(){
        return points.get(THIRD_POINT_INDEX);
    }
}
