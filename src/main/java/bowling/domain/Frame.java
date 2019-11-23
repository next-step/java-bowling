package bowling.domain;

import java.util.List;

public class Frame {
    private FrameNumber frameNumber;
    private Pins pins;

    public Frame(int frameNumber, Pins pins) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pins = pins;
    }

    public Frame(int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pins = new Pins(this.frameNumber.isLastFrame());
    }

    public void fallDown(int pin) {
       pins.fallDown(pin);
    }

    public int getScore() {
        return pins.score();
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    public boolean isEnd() {
        return !isFallDownAble();
    }

    public boolean isFallDownAble() {
        return addAblePinCount() > Pin.ZERO_PIN_COUNT;
    }

    public int addAblePinCount() {
        return pins.addAblePinCount(frameNumber.isLastFrame());
    }

    public boolean isStrike() {
        if (frameNumber.isLastFrame()) {
            return false;
        }
        return pins.isStrike();
    }

    public boolean isSpare() {
        if (frameNumber.isLastFrame()) {
            return false;
        }
        return pins.isSpare();
    }

    public List<Pin> unmodifiableBalls() {
        return pins.unmodifiableBalls();
    }
}
