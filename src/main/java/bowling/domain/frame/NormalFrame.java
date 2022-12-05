package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.exception.DoNotHaveEnoughPointsException;

public class NormalFrame extends AbstractFrame {
    private Frame next;

    public NormalFrame(int order) {
        super(order);
        this.state = new Ready();
    }

    @Override
    public Frame bowl(Point point) {
        state = state.bowl(point);
        if (state.isFinished()) {
            next = nextFrame();
            return next;
        }
        return this;
    }

    private Frame nextFrame() {
        if (isNextFrameIsLastFrame()) {
            return new LastFrame(order + 1);
        }
        return new NormalFrame(order + 1);
    }

    private boolean isNextFrameIsLastFrame() {
        return order + 1 == Frame.MAX_NUMBER;
    }

    @Override
    public Point calculateTotalPoint() {
        Score score = state.score();
        if (score.canReceiveExtraPoint()) {
            score = next.calculateExtraPoint(score);
        }
        return score.getPoint();
    }

    @Override
    public Score calculateExtraPoint(Score score) {
        score = state.addExtraPoint(score);
        if (score.canReceiveExtraPoint()) {
            return getExtraPointFromNext(score);
        }
        return score;
    }

    private Score getExtraPointFromNext(Score score) {
        if (next == null) {
            throw new DoNotHaveEnoughPointsException();
        }
        return next.calculateExtraPoint(score);
    }

}
