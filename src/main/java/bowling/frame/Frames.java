package bowling.frame;

import java.util.LinkedList;

public class Frames {

    LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames start() {
        return new Frames(new LinkedList<>());
    }

    public static Frames of(Frames frames, Frame frame) {
        LinkedList<Frame> currentFrame = new LinkedList<>(frames.frames);
        currentFrame.add(frame);
        return new Frames(currentFrame);
    }

    public int getFrameNumber() {
        return frames.getLast().getFrameNumber();
    }

    public boolean isNormalFinish() {
        return frames.get(this.frames.size() - 1).isFinal();
    }
}
