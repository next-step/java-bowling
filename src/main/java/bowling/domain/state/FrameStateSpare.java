package bowling.domain.state;

import bowling.domain.Pinfall;

public class FrameStateSpare implements  FrameState{
    @Override
    public FrameState roll(Pinfall pinfall) {
        return null;
    }

    @Override
    public boolean isRollable() {
        return false;
    }
}
