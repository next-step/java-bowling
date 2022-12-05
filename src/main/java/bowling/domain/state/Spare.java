package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Points;
import bowling.domain.Score;

import java.util.List;

public class Spare extends Finished {
    protected final Points points;

    public Spare(Point... points) {
        this.points = new Points(List.of(points));
    }

    @Override
    public Score score() {
        return Score.ofSpare(points.sum());
    }

    @Override
    public Score addExtraPoint(Score score) {
        return points.addExtraPoint(score);
    }

    @Override
    public List<Point> getPoints() {
        return points.getPoints();
    }

}
