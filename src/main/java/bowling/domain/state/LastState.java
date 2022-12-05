package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Points;
import bowling.domain.Score;
import bowling.exception.AlreadyFinishedException;
import bowling.exception.DoNotHaveEnoughPointsException;

import java.util.List;

public class LastState implements State {
    private static final int FULL_POINTS_SIZE = 3;
    private static final int NORMAL_POINTS_SIZE = 2;
    private final Points points = new Points();

    public LastState() {
    }

    @Override
    public State bowl(Point point) {
        if (isFinished()) {
            throw new AlreadyFinishedException();
        }
        points.add(point);
        return this;
    }

    @Override
    public Score score() {
        if (!isFinished()) {
            throw new DoNotHaveEnoughPointsException();
        }
        return new Score(points.sum(), 0);
    }

    @Override
    public Score addExtraPoint(Score score) {
        return points.addExtraPoint(score);
    }

    @Override
    public boolean isFinished() {
        if (points.hasStrikeOrSpare()) {
            return points.size() == FULL_POINTS_SIZE;
        }
        return points.size() == NORMAL_POINTS_SIZE;
    }

    @Override
    public List<Point> getPoints() {
        return points.getPoints();
    }

}
