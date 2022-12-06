package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;

import java.util.List;

public interface Frame {
    int MAX_NUMBER = 10;

    Frame bowl(Point point);

    List<Point> getPoints();

    Point calculateTotalPoint();

    Score calculateExtraPoint(Score score);

    int getOrder();

    boolean isFinished();
}
