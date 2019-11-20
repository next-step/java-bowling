package com.seok2.bowling.state.domain;

import com.seok2.bowling.frame.domain.Score;
import com.seok2.bowling.pin.domain.Pin;

public class Ready extends Running {

    public static Ready of() {
        return new Ready();
    }

    @Override
    public State roll(Pin felled) {
        if (felled.isAllFelled()) {
            return Strike.of();
        }
        return Cover.of(felled);
    }

    @Override
    public Score getScore() {
        return Score.ZERO;
    }

    @Override
    public String view() {
        return "";
    }
}
