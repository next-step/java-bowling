package bowling.domain;

import bowling.domain.state.InitState;
import bowling.domain.state.State;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:53
 */
public class NormalFrame extends Frame {
    private State state;
    private final FrameNumber frameNumber;

    public NormalFrame() {
        this.frameNumber = FrameNumber.init();
        this.state = InitState.of();
    }

    private NormalFrame(FrameNumber frameNumber) {
        this.state = InitState.of();
        this.frameNumber = frameNumber;
    }

    @Override
    public NormalFrame bowl(int fallCount) {
        if (isGameOver()) {
            // TODO FinalFrame 생성 반환
        }
        if(state.isOver()) {
            return nextFrame(fallCount);
        }
        state = state.update(Point.of(fallCount));
        return this;
    }

    @Override
    public boolean isGameOver() {
        return frameNumber.isOver();
    }

    @Override
    State getState() {
        return state;
    }

    private NormalFrame nextFrame(int fallCount) {
        NormalFrame newFrame = new NormalFrame(frameNumber.next());
        newFrame.bowl(fallCount);

        return newFrame;
    }
}
