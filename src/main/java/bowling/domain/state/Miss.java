package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Points;
import bowling.domain.Score;

import java.util.List;

public class Miss extends Finished {
    private final Points points;

    public Miss(Point... points) {
        this.points = new Points(List.of(points));
    }

    @Override
    public Score score() {
        return Score.ofMiss(points.sum());
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
