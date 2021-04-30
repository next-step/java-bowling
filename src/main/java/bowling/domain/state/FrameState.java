package bowling.domain.state;

import bowling.domain.Pinfall;

public interface FrameState {
    FrameState roll(Pinfall pinfall);

    boolean isRollable();
}
