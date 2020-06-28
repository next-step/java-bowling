package bowling.domain.point;

import java.util.ArrayList;
import java.util.List;

public class Points {
    private List<Point> points;

    private Points() {
        this.points = new ArrayList<>();
    }

    public static Points create() {
        return new Points();
    }

    public void add(Point point) {
        this.points.add(point);
    }

    public int sum() {
        return points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    public int size() {
        return points.size();
    }
}
