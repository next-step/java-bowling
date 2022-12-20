package bowling.model.frame;

import bowling.model.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.first());
    }

    public void bowl(Pin pin) {
        Frame frame = getCurrentFrame();
        frame.bowl(pin);
    }

    public Frame nextFrame() {
        Frame frame = getCurrentFrame();
        if (isFinished(frame)) {
            frames.add(frame.nextFrame());
        }
        return getCurrentFrame();
    }

    private boolean isFinished(Frame frame) {
        return frame.isFinished() && !frame.isFinalFrame();
    }

    public int size() {
        return frames.size();
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getNumber();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
