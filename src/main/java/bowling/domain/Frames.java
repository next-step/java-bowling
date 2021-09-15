package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private List<Frame> frames;

    public static final int FINAL_INDEX = 10;

    public Frames() {
        frames = new ArrayList<>();
    }

    public Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public boolean isFinished() {
        if (frames.size() == FINAL_INDEX &&
                frames.get(FINAL_INDEX - 1).isFinished()) {
            return true;
        }
        return false;
    }

    public List<Frame> values() {
        return frames;
    }
}
