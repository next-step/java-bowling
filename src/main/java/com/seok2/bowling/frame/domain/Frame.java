package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;

public interface Frame {

    static Frame normal() {
        return NormalFrame.of();
    }

    static Frame end() {
        return EndFrame.of();
    }

    void roll(Pin felled);

    boolean isEnd();

    FrameScore getScore();

}
