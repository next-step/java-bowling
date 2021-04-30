package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.awt.*;

public class NormalFrame extends Frame {


    State state;
    Frame next;
    int index;

    public NormalFrame(int index) {
        this.state = new Ready();
        this.index = index;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public Frame bowl(HitCount hitCount) {
        validateEnd();
        state = state.bowl(hitCount);
        System.out.println(state.getClass().getName());
        if (state.isFinish()) {
            next = generateNextFrame();
            return next;
        }
        return this;
    }

    private Frame generateNextFrame() {
        if (index >= 8) {
            return FinalFrame.initialize();
        }
        return new NormalFrame(index + 1);
    }

    private void validateEnd() {
        if (state.isFinish()) {
            throw new IllegalArgumentException();
        }
    }

    public int getScore(){
        return state.getScore();
    }
}
