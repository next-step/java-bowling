package bowling.domain.rule;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;

public abstract class Rule {

    private final FrameInfo frameInfo;
    private final Pins pins;

    protected Rule(FrameInfo frameInfo, Pins pins) {
        this.frameInfo = frameInfo;
        this.pins = pins;
    }

    public abstract boolean isEnd();

    public abstract boolean isCurrentRoundEnd();

    public abstract boolean hasNextRound();

    public abstract boolean isAfterFrame(int givenFrame);

}
