package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames start() {
        List<Frame> frames = new ArrayList<>();
        Frame firstFrame = NormalFrame.first();
        frames.add(firstFrame);
        return new Frames(frames);
    }

    public void bowl(Pin falledPins) {
        Frame lastFrame = getLastFrame();
        Frame frame = lastFrame.bowl(falledPins);
        if (frame != lastFrame) {
            frames.add(frame);
        }
    }

    public int currentFrameNumber() {
        if (getLastFrame().isFinished()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    public boolean isFinished() {
        if (frames.size() == FinalFrame.FINAL_FRAME_NUMBER && getLastFrame().isFinished()) {
            return true;
        }
        return false;
    }

    public int size() {
        return frames.size();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getValues() {
        return frames;
    }
}
