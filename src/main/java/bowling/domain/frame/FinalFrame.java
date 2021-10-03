package bowling.domain.frame;

import bowling.domain.state.State;

public class FinalFrame extends AbstractFrame {

    private FinalFrame(State state) {
        super(FINAL_ROUND, state);
    }

    public static FinalFrame of(State state) {
        return new FinalFrame(state);
    }

    @Override
    public Frame createNextFrame() {
        return null;
    }

}
