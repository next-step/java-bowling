package com.jaeyeonling.bowling.domain.frame;

public interface Frame {

    String getFrameState();
    Frame bowl(final KnockdownPins knockdownPins);
    boolean isFinish();
    FrameIndex getFrameIndex();
}
