package bowling.score;

import bowling.domain.point.Points;

@FunctionalInterface
public interface Markable {

    String getMark(Points points);
}
