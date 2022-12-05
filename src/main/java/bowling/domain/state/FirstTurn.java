package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;

import java.util.List;

public class FirstTurn extends Running {
    protected final Point point;

    public FirstTurn(Point point) {
        this.point = point;
    }

    @Override
    public State bowl(Point point) {
        Point sum = this.point.add(point);
        if (sum.isFull()) {
            return new Spare(this.point, point);
        }
        return new Miss(this.point, point);
    }

    @Override
    public Score addExtraPoint(Score score) {
        return score.addExtraPoint(point);
    }

    @Override
    public List<Point> getPoints() {
        return List.of(point);
    }

}
