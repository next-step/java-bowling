package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private List<Frame> frames = new ArrayList<>();

    public boolean isFinished() {
        return frames.size() == FinalFrame.FINAL_FRAME_NUM && getLastFrame().isFinished();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }
}
