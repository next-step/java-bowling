package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private Frame nextFrame;
    private static final int FINAL_NORMAL_FRAME = 9;
    private final int round;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();
    }

    @Override
    public Frame bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(round == FINAL_NORMAL_FRAME && this.state.isFinish()) {
            this.nextFrame = new FinalFrame();
            return this.nextFrame;
        }
        if(this.state.isFinish()) {
            this.nextFrame = new NormalFrame(this.round + 1);
            return this.nextFrame;
        }
        return this;
    }

    @Override
    public Frame nextFrame() {
        return this.nextFrame;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public String expression() {
        return null;
    }

}
