package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;

import java.util.List;

public class Strike extends Finished {
    private final Point point;

    public Strike(Point point) {
        this.point = point;
    }

    @Override
    public Score score() {
        return Score.ofStrike(point);
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
