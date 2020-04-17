package bowling.score;

import bowling.domain.Points;

@FunctionalInterface
public interface Markable {

    String getMark(Points points);
}
