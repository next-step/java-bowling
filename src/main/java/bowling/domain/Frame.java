package bowling.domain;

import bowling.FrameStateRenderer;

public interface Frame {
    void bowl(PinCount fallenPinCount);
    boolean isFinished();
    FrameStateRenderer toFrameStateRenderer();
}
