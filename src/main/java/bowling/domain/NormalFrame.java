package bowling.domain;

import bowling.domain.state.State;

public class NormalFrame implements Frame{

    private final State state = State.initialize();
    private final int index;

    private Frame next;

    public NormalFrame(int index) {
        this.index = index;
        this.next = Frame.initialize();
    }

    public static Frame initialize() {
        return null;
    }
}
