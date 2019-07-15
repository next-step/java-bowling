package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.domain.frame.state.FinalState;

public class FinalFrame extends Frame {

    FinalFrame() {
        super(new FinalState());
    }
}