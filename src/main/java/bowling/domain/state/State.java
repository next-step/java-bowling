package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;

import java.util.List;

public interface State {
    State bowl(Point point);

    Score score();

    Score addExtraPoint(Score score);

    boolean isFinished();

    List<Point> getPoints();
}
