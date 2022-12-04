package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Points {
    private final List<Point> points = new ArrayList<>();

    public Points() {
    }

    public Points(List<Point> points) {
        this.points.addAll(points);
    }

    public void add(Point point) {
        points.add(point);
    }

    public Point sum() {
        return points.stream()
                .reduce(Point.of(0), Point::add);
    }

    public Score addExtraPoint(Score score) {
        for (int i = 0; i < points.size() && score.canReceiveExtraPoint(); i++) {
            score = score.addExtraPoint(points.get(i));
        }
        return score;
    }

    public boolean hasStrikeOrSpare() {
        return sum().isFull();
    }

    public int size() {
        return points.size();
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

}
