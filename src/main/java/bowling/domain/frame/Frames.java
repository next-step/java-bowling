package bowling.domain.frame;

import bowling.domain.pin.Pin;

public class Frames {

    private final Frame firstFrame;
    private Frame currentFrame;

    private Frames() {
        this.firstFrame = Frame.init();
        this.currentFrame = firstFrame;
    }

    public static Frames init() {
        return new Frames();
    }

    public void bowl(Pin felledPin) {
        currentFrame = currentFrame.bowl(felledPin);
    }

    public boolean isGameOver() {
        return currentFrame.isEnd();
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }

    public Index getLastIndex() {
        return currentFrame.getIndex();
    }
}
