package bowling.domain;

import bowling.exception.InvalidPlayCountException;

public abstract class Frame {
    protected final int frameNumber;
    protected Frame nextFrame;
    private final Pins pins;
    protected int playCount = 0;

    public Frame(int frameNumber) {
        validateFrameNumber(frameNumber);
        this.frameNumber = frameNumber;
        this.pins = new Pins(frameNumber);
    }

    public void linkNextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public void bowl(int hittingNumber) {
        if (isEndFrame()) {
            throw new InvalidPlayCountException("횟수를 초과했습니다.");
        }
        validatePlayCount();
        pins.play(hittingNumber);
        playCount++;
    }

    public int getScore() {
        if (isEndFrame() && frameNumber == 10) {
            return pinStatus().totalPin();
        }
        if (isEndFrame() && pins.pinStatus().firstPin() == 10 && frameNumber != 10) {
            return nextFrame.additionalScore(2, 10);
        }

        if (isEndFrame() && pins.pinStatus().totalPin() == 10 && frameNumber != 10) {
            return nextFrame.additionalScore(1, 10);
        }

        if (isEndFrame() && frameNumber != 10) {
            return pins.pinStatus().totalPin();
        }
        return -1;
    }

    protected abstract int additionalScore(int leftBonusCount, int sumScore);

    public PinStatus pinStatus() {
        return pins.pinStatus();
    }

    public int frameNumber() {
        return frameNumber;
    }

    public int playCount() {
        return playCount;
    }

    abstract void validateFrameNumber(int frameNumber);

    abstract void validatePlayCount();

    public abstract boolean isEndFrame();


}
