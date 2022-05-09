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
        return frameInfo.isFrameEnd() && (pins.isAllDown() || (frameInfo.isSecondRound() && !pins.isReady()));
    }

    @Override
    public boolean isNewPins() {
        return pins.isAllDown() || (frameInfo.isSecondRound() && !pins.isReady());
    }

    @Override
    public boolean isFrameEnd(int givenFrame) {
        return frameInfo.isFrameEnd(givenFrame);
    }

    @Override
    public boolean hasNextRound() {
        return !frameInfo.isSecondRound() || pins.isReady();
    }
}
