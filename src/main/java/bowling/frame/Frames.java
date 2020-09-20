package bowling.frame;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static bowling.global.utils.CommonConstant.NUMBER_ONE;

public class Frames {

    LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames saveScore(LinkedList<Frame> frames, Frame frame) {
        LinkedList<Frame> currentFrame = frames;
        currentFrame.add(frame);
        return new Frames(currentFrame);
    }

    public int getFrameNumber() {
        return frames.getLast().getFrameNumber();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isNormalFinish() {
        int frameIndex = this.frames.size() - NUMBER_ONE;
        return frames.get(frameIndex).isFinal();
    }

}
