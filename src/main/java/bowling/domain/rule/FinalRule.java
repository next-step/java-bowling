package bowling.domain.rule;

import bowling.domain.frame.info.FrameInfo;
import bowling.domain.pins.Pins;

public class FinalRule extends Rule {

    private final FrameInfo frameInfo;
    private final Pins pins;

    public FinalRule(FrameInfo frameInfo, Pins pins) {
        super(frameInfo, pins);
        this.frameInfo = frameInfo;
        this.pins = pins;
    }

    @Override
    public boolean isEnd() {
        return !pins.isReady() && (validateSecondRound() || (frameInfo.isFrameEnd()));
    }

    private boolean validateSecondRound() {
        return frameInfo.isSecondRound() && !pins.isAllDown() && !frameInfo.hasNextRound();
    }

    @Override
    public boolean isNewPins() {
        return pins.isAllDown();
    }

    @Override
    public boolean hasNextRound() {
        return !frameInfo.isSecondRound() || pins.isReady();
    }

    @Override
    public boolean isFrameEnd(int givenFrame) {
        return isEnd() || frameInfo.isFrameEnd(givenFrame);
    }
}
