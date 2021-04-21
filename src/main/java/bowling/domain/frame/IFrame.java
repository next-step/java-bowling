package bowling.domain.frame;

import bowling.domain.state.FrameState;

public interface IFrame {
    FrameState bowl(int pins);
}
