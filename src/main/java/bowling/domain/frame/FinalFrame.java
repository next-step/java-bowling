package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.exception.frame.FinalFrameCreateFrameException;

public class FinalFrame extends AbstractFrame {

    private FinalFrame(State state) {
        super(FINAL_ROUND, state);
    }

    public static FinalFrame of(State state) {
        return new FinalFrame(state);
    }

    @Override
    public Frame createNextFrame() {
        throw new FinalFrameCreateFrameException();
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

}
