package bowling.domain.state;

import bowling.domain.Pinfall;

public class FrameStateReady implements FrameState {
    @Override
    public FrameState roll(Pinfall pinfall) {
        if (pinfall.isStrike()) {
            return new FrameStateStrike();
        }
        return new FrameStateOnce();
    }

    @Override
    public boolean isRollable() {
        return true;
    }
}
