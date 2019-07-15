package com.jaeyeonling.bowling.domain.frame.state;

abstract class Running extends FrameState {

    @Override
    public boolean isFinished() {
        return false;
    }
}
