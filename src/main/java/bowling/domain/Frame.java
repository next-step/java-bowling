package bowling.domain;

import java.util.List;

public class Frame {
    private int frameNumber;
    private Balls balls;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.balls = new Balls(isLastFrame());
    }

    public void fallDown(int pin) {
       balls.fallDown(pin);
    }

    public boolean isLastFrame() {
        return frameNumber == Frames.LAST_FRAME;
    }

    public int getScore() {
        return balls.score();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean isFallDownAble() {
        return addAblePinCount() > Ball.ZERO_PIN_COUNT;
    }

    public int addAblePinCount() {
        return balls.addAblePinCount(isLastFrame());
    }

    public boolean isSpare() {
        return balls.isSpare();
    }

    public List<Ball> unmodifiableBalls() {
        return balls.unmodifiableBalls();
    }
}
