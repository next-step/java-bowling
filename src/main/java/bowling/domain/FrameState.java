package bowling.domain;

import bowling.FrameStateRenderer;

public interface FrameState {
    FrameState bowl(PinCount pinCount);
    FrameStateRenderer toRenderer();
}
