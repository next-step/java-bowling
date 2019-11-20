package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.state.domain.Ready;
import com.seok2.bowling.state.domain.State;

public class NormalFrame implements Frame {

    private State state = Ready.of();

    public static Frame of() {
        return new NormalFrame();
    }

    @Override
    public void roll(Pin felled) {
        this.state = state.roll(felled);
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    protected State getState() {
        return state;
    }
}
