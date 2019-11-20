package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.state.domain.EndFrameStates;

public class EndFrame implements Frame {

    private final EndFrameStates states = new EndFrameStates();

    public static Frame of() {
        return new EndFrame();
    }

    protected EndFrameStates getStates() {
        return states;
    }

    @Override
    public void roll(Pin felled) {
        states.roll(felled);
    }

    @Override
    public boolean isEnd() {
        return states.isEnd();
    }

    @Override
    public FrameScore getScore() {
        if(!isEnd())
            return FrameScore.PENDING;
        return states.getScore();
    }
}
