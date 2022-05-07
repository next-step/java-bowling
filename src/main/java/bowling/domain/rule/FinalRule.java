package bowling.domain.rule;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;

public class FinalRule extends Rule {
    
    protected FinalRule(FrameInfo frameInfo, Pins pins) {
        super(frameInfo, pins);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isCurrentRoundEnd() {
        return false;
    }

    @Override
    public boolean hasNextRound() {
        return false;
    }

    @Override
    public boolean isAfterFrame(int givenFrame) {
        return false;
    }
}
