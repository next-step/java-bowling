package bowling.domain.frame;

import java.util.HashMap;
import java.util.Map;

import static bowling.utils.BowlingConstants.MAX_FRAME_COUNT;

public class Frames {

    private Map<Integer, Frame> frames;

    public Frames() {
        frames = new HashMap<>();
    }

    public void addToFrames(int frameNum, Frame frame) {
        frames.put(frameNum, frame);
    }

    public boolean isMaxFrameCount() {
        return this.frames.size() == MAX_FRAME_COUNT;
    }

    public boolean isLastFrameFinished() {
        return this.frames.get(MAX_FRAME_COUNT).isCompleted();
    }

    public Frame findFrameByFrameNumber(int num) {
        return frames.get(num);
    }
}
