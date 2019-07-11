package com.jaeyeonling.bowling.frame;

import com.jaeyeonling.bowling.view.StringVisualizable;

public interface Frame extends StringVisualizable {

    Frame bowl(final KnockdownPins knockdownPins);
    boolean isFinish();

    static Frame first() {
        return NormalFrame.of(FrameIndex.first());
    }
}
