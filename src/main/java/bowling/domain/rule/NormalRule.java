package bowling.domain.rule;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;

public class NormalRule extends Rule {

    private final FrameInfo frameInfo;
    private final Pins pins;

    public NormalRule(FrameInfo frameInfo, Pins pins) {
        super(frameInfo, pins);
        this.frameInfo = frameInfo;
        this.pins = pins;
    }

    @Override
    public boolean isEnd() {
        return frameInfo.isEndFrame() && isCurrentRoundEnd();
    }

    @Override
    public boolean isCurrentRoundEnd() {
        return pins.isAllDown() || frameInfo.isLastRound();
    }

    @Override
    public boolean isAfterFrame(int givenFrame) {
        return frameInfo.isAfterFrame(givenFrame);
    }

    @Override
    public boolean hasNextRound() {
        return !frameInfo.isLastRound() || pins.isReady();
    }
}
