package bowling.domain.bowl;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameEnum;
import bowling.domain.frame.Pin;
import bowling.dto.PinsDto;

abstract class BowlState {
    private Frame currentFrame;

    BowlState() {
        currentFrame = new Frame();
    }

    BowlState(BowlState state) {
        currentFrame = state.currentFrame;
    }

    void updateCurrentFrame(Pin pin) {
        currentFrame.addPin(pin);
    }

    void addNextFrame(Bowl bowl) {
        currentFrame = currentFrame.generateNextFrame();
        bowl.addFrame(currentFrame);
    }

    FrameEnum getFrameEnum() {
        return currentFrame.getFrameEnum();
    }

    abstract boolean isPlayable();

    abstract void addPin(Pin pin, Bowl bowl);

    abstract void updateState(Bowl bowl);

    PinsDto exportPinsDto() {
        return currentFrame.exportPinsDto();
    }
}
