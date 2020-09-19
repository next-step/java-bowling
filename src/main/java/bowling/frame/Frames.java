package bowling.frame;

import java.util.LinkedList;

import static bowling.global.utils.CommonConstant.NUMBER_ONE;

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
        int frameIndex = this.frames.size() - NUMBER_ONE;
        return frames.get(frameIndex).isFinal();
    }

    @Override
    public String toString() {
        return String.valueOf(frames);
    }
}
