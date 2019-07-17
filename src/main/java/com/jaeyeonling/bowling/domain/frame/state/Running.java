package com.jaeyeonling.bowling.domain.frame.state;

abstract class Running implements FrameState {

    @Override
    public boolean isFinished() {
        return false;
    }
}
