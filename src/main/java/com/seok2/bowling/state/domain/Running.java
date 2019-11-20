package com.seok2.bowling.state.domain;

public abstract class Running implements State {

    @Override
    public boolean isEnd() {
        return false;
    }
}
