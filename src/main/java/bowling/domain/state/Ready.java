package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.DoNotHaveEnoughPointsException;

import java.util.List;

public class Ready extends Running {
    @Override
    public State bowl(Point point) {
        if (point.isFull()) {
            return new Strike(point);
        }
        return new FirstTurn(point);
    }

    @Override
    public Score addExtraPoint(Score score) {
        throw new DoNotHaveEnoughPointsException();
    }

    @Override
    public List<Point> getPoints() {
        throw new DoNotHaveEnoughPointsException();
    }
}
