package bowling.domain;

import java.util.List;

public class Frame {
    private int frameNumber;
    private Pins pins;

    public Frame(int frameNumber, Pins pins) {
        this.frameNumber = frameNumber;
        this.pins = pins;
    }

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.pins = new Pins(isLastFrame());
    }

    public void fallDown(int pin) {
       pins.fallDown(pin);
    }

    public boolean isLastFrame() {
        return frameNumber == Frames.LAST_FRAME;
    }

    public int getScore() {
        return pins.score();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean isEnd() {
        return !isFallDownAble();
    }

    public boolean isFallDownAble() {
        return addAblePinCount() > Pin.ZERO_PIN_COUNT;
    }

    public int addAblePinCount() {
        return pins.addAblePinCount(isLastFrame());
    }

    public boolean isStrike() {
        if (isLastFrame()) {
            return false;
        }
        return pins.isStrike();
    }

    public boolean isSpare() {
        if (isLastFrame()) {
            return false;
        }
        return pins.isSpare();
    }

    public List<Pin> unmodifiableBalls() {
        return pins.unmodifiableBalls();
    }
}
