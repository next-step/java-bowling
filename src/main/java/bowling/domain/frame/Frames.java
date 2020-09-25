package bowling.domain.frame;

import bowling.domain.pin.Pin;
import java.util.LinkedList;

public class Frames {

    private LinkedList<Frame> frames;

    private Frames() {
        Frame firstFrame = Frame.init();
        this.frames = new LinkedList<>();
        frames.add(firstFrame);
    }

    public static Frames init() {
        return new Frames();
    }

    public void bowl(Pin felledPin) {
        Frame currentFrame = frames.getLast().bowl(felledPin);
        frames.add(currentFrame);
    }

    public boolean isGameOver() {
        return frames.getLast().isEnd();
    }

    public Frame getFirstFrame() {
        return frames.getFirst();
    }

    public Index getLastIndex() {
        return frames.getLast().getIndex();
    }
}
