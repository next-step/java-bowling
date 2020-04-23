package bowling.domain.point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {
    public static final int MAX_POINT = 10;
    public static final int MIN_POINT = 0;
    private static final Map<Integer, Point> POINT = new HashMap<>();

    private final int point;

    static {
        for (int i = MIN_POINT; i <= MAX_POINT; i++) {
            POINT.put(i, new Point(i));
        }
    }

    public static Point of(int input) {
        validate(input);
        return POINT.get(input);
    }

    private Point(int input) {
        validate(input);
        this.point = input;
    }

    public int getPoint() {
        return point;
    }

    public boolean isMaxPoint() {
        return point == MAX_POINT;
    }

    public boolean isMinPoint() {
        return point == MIN_POINT;
    }

    public int add(Point targetPoint) {
        return this.point + targetPoint.getPoint();
    }

    public Point getLeftPoint() {
        return Point.of(MAX_POINT - point);
    }

    public boolean isOver(Point secondPoint) {
        return (this.point + secondPoint.getPoint()) > MAX_POINT;
    }

    public String print() {
        if (isMaxPoint()) {
            return "X";
        }
        if (isMinPoint()) {
            return "-";
        }
        return String.valueOf(point);
    }

    private static void validate(int input) {
        if (input < MIN_POINT || input > MAX_POINT) {
            throw new PointOutOfRangeException(String.format("포인트는 %s ~ %s 사이만 가능합니다.", MIN_POINT, MAX_POINT));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
