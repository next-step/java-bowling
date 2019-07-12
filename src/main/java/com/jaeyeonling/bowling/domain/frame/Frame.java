package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.view.StringVisualizable;

public interface Frame extends StringVisualizable {

    Frame bowl(final KnockdownPins knockdownPins);
    boolean isFinish();
    FrameIndex getFrameIndex();

    static Frame first() {
        return NormalFrame.of(FrameIndex.first());
    }
}
