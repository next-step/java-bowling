package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames start(Pin falledPins) {
        List<Frame> frames = new ArrayList<>();
        NormalFrame firstFrame = new NormalFrame(1, falledPins);
        frames.add(firstFrame);
        return new Frames(frames);
    }

    public void bowl(Pin falledPins) {
        Frame lastFrame = getLastFrame();
        if (lastFrame.isFinished()) {
            frames.add(lastFrame.nextFrame(falledPins));
            return;
        }
        lastFrame.bowl(falledPins);
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

    public List<Frame> getValues() {
        return frames;
    }

    public int size() {
        return frames.size();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }
}
