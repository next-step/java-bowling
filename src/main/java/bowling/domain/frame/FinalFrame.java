package bowling.domain.frame;

import bowling.domain.state.States;

public final class FinalFrame extends Frame {

    private final States states;
    private final FinalRound finalRound;

    private FinalFrame() {
        this.states = States.initialize();
        this.finalRound = FinalRound.initialize();
    }

    public static final Frame initialize() {
        return new FinalFrame();
    }

}
