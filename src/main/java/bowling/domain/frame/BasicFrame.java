package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;

public class BasicFrame extends AbstractFrame {

    private State state;

    public BasicFrame(final FrameNumber frameNumber) {
        super(frameNumber);
        this.state = StateFactory.ready();
    }

    @Override
    public Frame play(final PinCount pinCount) {
        state = this.state.play(pinCount);
        if (state.isFinished()) {
            return createFrame();
        }
        return this;
    }

    private Frame createFrame() {
        FrameNumber nextFrameNumber = getFrameNumber().next();
        if (nextFrameNumber.isFinalNumber()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new BasicFrame(nextFrameNumber);
    }

    @Override
    public FrameResult makeResult() {
        return new FrameResult(state.showIndication());
    }
}
