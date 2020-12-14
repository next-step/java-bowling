package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameEnum;
import bowling.domain.frame.Pin;
import bowling.dto.PinsDto;

import static bowling.asset.Const.MAX_FRAME_NO;

abstract class BowlState {
    private Frame currentFrame;
    private int frameNumber = 1;

    BowlState() {
        currentFrame = new Frame();
    }

    BowlState(BowlState state) {
        frameNumber = state.frameNumber;
        currentFrame = state.currentFrame;
    }

    void updateCurrentFrame(Pin pin) {
        currentFrame.addPin(pin);
    }

    void addNextFrame(Bowl bowl) {
        currentFrame = currentFrame.generateNextFrame();
        bowl.addFrame(currentFrame);
        frameNumber = bowl.getFrameNumber();
    }

    boolean isLast() {
        return frameNumber > MAX_FRAME_NO;
    }

    boolean isStrike() {
        return currentFrame.getFrameEnum() == FrameEnum.STRIKE;
    }

    boolean isSpare() {
        return currentFrame.getFrameEnum() == FrameEnum.SPARE;
    }

    abstract boolean isPlayable();

    abstract void addPin(Pin pin, Bowl bowl);

    abstract void updateState(Bowl bowl);

    PinsDto exportPinsDto() {
        return currentFrame.exportPinsDto();
    }
}
