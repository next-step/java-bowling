package bowling.domain.frame;

import bowling.domain.Point;

public interface Frame {
    void bowl(Point point);
    boolean isStrike();
    boolean isSpare();
}
