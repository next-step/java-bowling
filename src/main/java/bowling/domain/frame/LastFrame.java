package bowling.domain.frame;

import bowling.domain.state.LastInProgress;
import bowling.domain.state.State;

import java.util.List;

public class LastFrame extends Frame {
    protected LastFrame(State state) {
        super(state);
    }

    public static Frame init() {
        return new LastFrame(LastInProgress.init());
    }

    @Override
    protected void appendFrame(List<Frame> frames) {
        // do nothing
    }
}
