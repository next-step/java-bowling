package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.domain.state.LastState;
import bowling.exception.DoNotHaveEnoughPointsException;

public class LastFrame extends AbstractFrame {
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
