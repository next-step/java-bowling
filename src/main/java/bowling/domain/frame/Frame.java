package bowling.domain.frame;

import bowling.domain.point.Point;

public interface Frame {

    Frame bowl(Point point);

    boolean isLastFrame();
}
