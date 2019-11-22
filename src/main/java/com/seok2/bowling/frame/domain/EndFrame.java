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
    public Frame roll(Pin felled) {
        states.roll(felled);
        return this;
    }

    @Override
    public boolean isEnd() {
        return states.isEnd();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException("마지막 프레임 입니다.");
    }

    @Override
    public Index getIndex() {
        return Index.of(Index.MAX);
    }

    @Override
    public Score getScore() {
        return states.getScore();
    }

    @Override
    public Score calculateScore(Score base) {
        return states.calculate(base);
    }
}
