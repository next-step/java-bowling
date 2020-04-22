package bowling.domain.status;

import bowling.domain.point.Point;

public interface Status {

    Status throwBall(Point point);

    boolean isEnd();
}
