package bowling.domain.point;

import bowling.domain.frame.FrameResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Points {
    private Map<Ordinal, Point> points;

    public Points(Map<Ordinal, Point> points) {
        this.points = points;
    }

    public static Points of(int firstPoint, int secondPoint) {
        Map<Ordinal, Point> points = new HashMap<>();
        points.put(Ordinal.FIRST, Point.of(firstPoint));
        points.put(Ordinal.SECOND, Point.of(secondPoint));
        return new Points(points);
    }

    public static Points of(int firstPoint, int secondPoint, int thirdPoint) {
        Map<Ordinal, Point> points = new HashMap<>();
        points.put(Ordinal.FIRST, Point.of(firstPoint));
        points.put(Ordinal.SECOND, Point.of(secondPoint));
        points.put(Ordinal.THIRD, Point.of(thirdPoint));
        return new Points(points);
    }

    public Map<Ordinal, Point> getPoints() {
        return Collections.unmodifiableMap(points);
    }

    public int getPointSize() {
        return this.points.size();
    }

    public FrameResult findResult() {
        Point first = points.get(Ordinal.FIRST);
        Point second = points.get(Ordinal.SECOND);

        return FrameResult.findResult(first.getScore(), second.getScore());
    }

    public void addThirdPoint(Point point) {
        this.points.put(Ordinal.THIRD, point);
    }

    public boolean isStrikeOrSpare() {
        return findResult().equals(FrameResult.SPARE) || findResult().equals(FrameResult.STRIKE);
    }
}