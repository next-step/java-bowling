package bowling.domain;

import bowling.domain.state.State;

import static bowling.util.BowlingFixture.FRAME_START_INDEX;

public class NormalFrame implements Frame {

    private final State state = State.initialize();
    private final int index;

    private Frame next;

    public NormalFrame(int index) {
        this.index = index;
        this.next = Frame.initialize();
    }

    public static final Frame initialize() {
        return new NormalFrame(FRAME_START_INDEX);
    }
}
