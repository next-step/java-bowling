package bowling.domain;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private Frame nextFrame;

    public NormalFrame() {
        this.state = new Ready();
    }

    @Override
    public State bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(frameCount() != 9 && !(this.state instanceof FirstBowl)) {
            this.nextFrame = new NormalFrame();
        }
        if(frameCount() == 9 && !(this.state instanceof FirstBowl)) {
            this.nextFrame = new FinalFrame();
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

    public int frameCount() {
        int count = 1;
        Frame frame = this.nextFrame;
        while(frame != null) {
            frame = frame.nextFrame();
            count++;
        }
        return count;
    }

}
