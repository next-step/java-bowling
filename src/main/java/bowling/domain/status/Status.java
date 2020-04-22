package bowling.domain.status;

import bowling.domain.point.Point;
import bowling.domain.score.Score;

public interface Status {

    Status throwBall(Point point);

    boolean isEnd();

    String print();

    Score getScore();
}
