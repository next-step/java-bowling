package bowling.domain;

import bowling.domain.state.InitState;
import bowling.exception.OutOfBowlCountException;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 15:29
 */
public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber) {
        this.state = InitState.of();
        this.frameNumber = new FrameNumber(frameNumber);
    }

    @Override
    public Frame bowl(int fallCount) {
        if (isGameOver()) {
            throw new OutOfBowlCountException();
        }
        state = state.update(Point.of(fallCount), true);
        return this;
    }

    @Override
    public boolean isGameOver() {
        return state.isOver(true);
    }

    @Override
    public Score getScore() {
        return state.stateScore();
    }

    @Override
    public Score updateScore(Score source) {
        return source;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "state=" + state +
                ", frameNumber=" + frameNumber +
                '}';
    }
}