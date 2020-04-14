package bowling.domain.point;

import bowling.domain.RandomGenerator;
import bowling.domain.frame.FrameResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static bowling.domain.point.Ordinal.*;

public class Points {
    private static final RandomGenerator RANDOM_GENERATOR = new RandomGenerator();

    private Map<Ordinal, Point> points;

    public Points(Map<Ordinal, Point> points) {
        this.points = Collections.unmodifiableMap(points);
    }

    public static Points of(Map<Ordinal, Point> points) {
        return new Points(points);
    }

    public static Points of(int firstPoint, int secondPoint) {
        return Points.of(firstPoint, secondPoint, false, false);
    }

    public static Points of(int first, int second, boolean needOnePoint, boolean needTwoPoints) {
        Map<Ordinal, Point> points = new HashMap<>();
        points.put(FIRST, Point.of(first));
        points.put(SECOND, Point.of(second));

        if (needOnePoint) {
            points.put(THIRD, Point.of(RANDOM_GENERATOR.getThirdPoint()));
        }

        if (needTwoPoints) {
            points.put(THIRD, Point.of(RANDOM_GENERATOR.getThirdPointForStrike()));
            points.put(FOURTH, Point.of(RANDOM_GENERATOR.getFourthPointForStrike()));
        }

        return Points.of(points);
    }

    public int getPointSize() {
        return this.points.size();
    }

    public FrameResult findResult() {
        Point first = points.get(FIRST);
        Point second = points.get(SECOND);

        return FrameResult.findResult(first.getScore(), second.getScore());
    }

    public int sum() {
        return points.keySet().stream()
                .filter(it -> Ordinal.values() != null)
                .map(it -> points.get(it))
                .mapToInt(Point::getScore)
                .sum();
    }

    public boolean containsOrdinal(Ordinal ordinal) {
        return points.keySet().contains(ordinal);
    }

    public int getFirstPoint() {
        return points.get(FIRST).getScore();
    }

    public int getSecondPoint() {
        return points.get(SECOND).getScore();
    }

    public int getThirdPoint() {
        return points.get(THIRD).getScore();
    }

    public int getFourthPoint() {
        return points.get(FOURTH).getScore();
    }
}