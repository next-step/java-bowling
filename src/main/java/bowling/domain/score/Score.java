package bowling.domain.score;

import bowling.domain.BowlType;
import bowling.domain.point.Point;

import java.util.List;

public interface Score {

    void pitch(Point pitchedPoint);

    boolean hasScoreTurn();

    BowlType getBowlType();

    List<Point> getPitchedPoint();

    int sumPoint();


}
