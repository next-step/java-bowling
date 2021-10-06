package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;

public class NormalFrame implements Frame {
    private int round;
    private State state;
    private Frame nextFrame;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();
        if (round + 1 < 10) {
            this.nextFrame = new NormalFrame(round + 1);
        }
    }

    public void throwBowl(int falledPins) {
        state = state.throwBowl(falledPins);
    }

    public State state() {
        return state;
    }

    public String getScore() {
        return state.getScore();
    }
}
