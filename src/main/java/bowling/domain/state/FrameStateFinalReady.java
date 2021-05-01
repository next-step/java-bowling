package bowling.domain.state;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbols;

public class FrameStateFinalReady implements FrameState {
    @Override
    public FrameState roll(Pinfall pinfall) {
        if (pinfall.isStrike()){
            return new FrameStateBonus(pinfall);
        }
        return new FrameStateFinalOnce(pinfall);
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
