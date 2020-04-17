package bowling.score;

import bowling.domain.Point;

import java.util.List;

@FunctionalInterface
public interface Markable {

    String getMark(List<Point> points);
}
