package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;

public class FrameStateReady implements FrameState {
    @Override
    public FrameState roll(Pinfall pinfall) {
        if (pinfall.isStrike()) {
            return new FrameStateStrike();
        }
        return new FrameStateOnce(pinfall);
    }

    @Override
    public boolean isRollable() {
        return true;
    }

    @Override
    public PointSymbols pointSymbols() {
        return new PointSymbols();
    }
}
