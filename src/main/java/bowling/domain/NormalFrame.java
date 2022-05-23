package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private Frame nextFrame;
    private final int round;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();
    }

    @Override
    public State bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(this.state.isFinish()) {
            nextFrame = new NormalFrame(this.round + 1);
        }
        if(round == 9 && this.state.isFinish()) {
            nextFrame = new FinalFrame();
        }
        return this.state;
    }

    @Override
    public Frame nextFrame() {
        return this.nextFrame;
    }

    @Override
    public State getState() {
        return this.state;
    }

}
