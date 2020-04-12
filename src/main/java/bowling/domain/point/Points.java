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
        points = Collections.unmodifiableMap(points);
    }

    public void addThirdPointForStrike(Point point){
        this.points.put(Ordinal.THIRD, point);
    }

    public void addFourthPointForStrike(Point point) {
        this.points.put(Ordinal.FOURTH, point);
        points = Collections.unmodifiableMap(points);
    }

    public boolean isStrikeOrSpare() {
        return findResult().equals(FrameResult.SPARE) || findResult().equals(FrameResult.STRIKE);
    }

    public boolean isStrike() {
        return findResult().equals(FrameResult.STRIKE);
    }

    public boolean isSpare() {
        return findResult().equals(FrameResult.SPARE);
    }

    public int getFirstPoint(){
        return points.get(Ordinal.FIRST).getScore();
    }

    public int getSecondPoint(){
        return points.get(Ordinal.SECOND).getScore();
    }

    public int getThirdPoint(){
        return points.get(Ordinal.THIRD).getScore();
    }

    public int getFourthPoint(){
        return points.get(Ordinal.FOURTH).getScore();
    }

    public boolean containsOrdinal(Ordinal ordinal){
        return points.keySet().contains(ordinal);
    }
}