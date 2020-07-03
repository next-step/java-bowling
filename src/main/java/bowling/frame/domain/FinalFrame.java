package bowling.frame.domain;

import bowling.pin.domain.Pin;
import bowling.state.domain.FinalFrameStates;

public class FinalFrame implements Frame {

    private final FinalFrameStates states = new FinalFrameStates();

    public static Frame of() {
        return new FinalFrame();
    }

    protected FinalFrameStates getStates() {
        return states;
    }

    @Override
    public void roll(Pin felled) {
        states.roll(felled);
    }

    @Override
    public boolean isEnd() {
        return states.isEnd();
    }

    @Override
    public FrameScore getScore() {
        if (!isEnd()) {
            return FrameScore.PENDING;
        }
        return states.getScore();
    }

}
