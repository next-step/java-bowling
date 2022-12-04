package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.domain.state.LastState;
import bowling.domain.state.State;
import bowling.exception.DoNotHaveEnoughPointsException;

import java.util.List;

public class LastFrame extends AbstractFrame {
    private State state;

    public LastFrame(int order) {
        super(order);
        this.state = new LastState();
    }

    @Override
    public Frame bowl(Point point) {
        state = state.bowl(point);
        if (state.isFinished()) {
            return null;
        }
        return this;
    }

    @Override
    public List<Point> getPoints() {
        return state.getPoints();
    }

    @Override
    public Point calculateTotalPoint() {
        return state.score().getPoint();
    }

    @Override
    public Score calculateExtraPoint(Score score) {
        score = state.addExtraPoint(score);
        if (score.canReceiveExtraPoint()) {
            throw new DoNotHaveEnoughPointsException();
        }
        return score;
    }

}
