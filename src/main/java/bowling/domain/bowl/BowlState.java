package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameEnum;
import bowling.domain.frame.Pin;
import bowling.dto.PinsDto;

import static bowling.asset.Const.MAX_FRAME_NO;

abstract class BowlState {
    private int frameNumber = 1;
    private Frame currentFrame;

    BowlState() {
        currentFrame = new Frame();
    }

    BowlState(BowlState state) {
        frameNumber = state.frameNumber;
        currentFrame = state.currentFrame;
    }

    int getFrameNumber() {
        return frameNumber;
    }

    void addPin(Pin pin, Bowl bowl) {
        currentFrame.addPin(pin);
        if (isFinished()) {
            bowl.addFrame(currentFrame);
            currentFrame = currentFrame.generateNextFrame();
            frameNumber++;
        }
        updateState(bowl);
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

    private boolean isFinished() {
        return currentFrame.getFrameEnum() != FrameEnum.UNFINISHED;
    }

    abstract boolean isPlayable();

    abstract void updateState(Bowl bowl);

    PinsDto exportPinsDto() {
        return currentFrame.exportPinsDto();
    }
}
