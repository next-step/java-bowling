package domain.frame;

import domain.Pins;
import domain.bowling.Bowling;
import domain.bowling.ReadySet;
import domain.state.State;
import domain.state.Waiting;

public class NormalFrame implements Frame {

    private Bowling bowling;
    private State state;
    private Frame next;

    public NormalFrame(Frame nextFrame) {
        this.next = nextFrame;
        this.bowling = new ReadySet();
        this.state = new Waiting();
    }

    @Override
    public Frame setKnockedDownPins(Pins knockedDown) {
        bowling = bowling.bowl(knockedDown);
        state = bowling.getFrameState();
        return this;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public boolean isClosed() {
        return state.isClosed();
    }
}
