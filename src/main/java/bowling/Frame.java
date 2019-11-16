package bowling;

public class Frame {
    private static final int ZERO_PIN_COUNT = 0;
    private int frameNumber;
    private Balls balls;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public void addBall(int pin) {
       balls.add(pin, isLastFrame());
    }

    private boolean isLastFrame() {
        return frameNumber == Frames.LAST_FRAME;
    }

    public int getScore() {
        return balls.score();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean isAddAble() {
        return addAblePinCount() > ZERO_PIN_COUNT;
    }

    public int addAblePinCount() {
        return balls.addAblePinCount();
    }
}
