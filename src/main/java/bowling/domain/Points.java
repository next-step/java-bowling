package bowling.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Points {
    private Map<Ordinal, Point> points;

    public Points(Map<Ordinal, Point> points) {
        this.points = Collections.unmodifiableMap(points);
    }

    public static Points of(int firstPoint, int secondPoint){
        Map<Ordinal, Point> points = new HashMap<>();
        points.put(Ordinal.FIRST, Point.of(firstPoint));
        points.put(Ordinal.SECOND, Point.of(secondPoint));
        return new Points(points);
    }

    public static Points of(int firstPoint, int secondPoint, int thirdPoint){
        Map<Ordinal, Point> points = new HashMap<>();
        points.put(Ordinal.FIRST, Point.of(firstPoint));
        points.put(Ordinal.SECOND, Point.of(secondPoint));
        points.put(Ordinal.THIRD, Point.of(thirdPoint));
        return new Points(points);
    }

    public Map<Ordinal, Point> getPoints() {
        return points;
    }

    public int getPointSize(){
        return this.points.size();
    }
}
