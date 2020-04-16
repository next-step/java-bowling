package bowling.domain.point;

import java.util.ArrayList;
import java.util.List;

/**
 * 포인트의 일급 컬렉션
 */
public class Points {
    private final List<Point> points;

    public Points() {
        this(new ArrayList<>());
    }

    private Points(List<Point> points) {
        this.points = points;
    }

    public int getTotalBonusPoint() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public void add(Point point) {
        points.add(point);
    }

    public int size() {
        return points.size();
    }
}
