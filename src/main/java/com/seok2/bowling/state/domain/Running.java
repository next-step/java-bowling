package com.seok2.bowling.state.domain;

import com.seok2.bowling.pin.domain.Pin;

public abstract class Running implements State{

    @Override
    public boolean isEnd() {
        return false;
    }
}
