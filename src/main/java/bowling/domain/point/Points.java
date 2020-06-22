package bowling.domain.point;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private List<Point> points;

    public Points() {
        this.points = new ArrayList<>();
    }

    public void addPoint(int point) {
        this.points.add(new Point(point));
    }

    public int sumPoints() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }
}
