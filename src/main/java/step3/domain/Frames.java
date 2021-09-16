package step3.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int END_FRAME_NUMBER = 10;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public Frame add(Frame frame) {
        if (frame.isFinish() && frame.number() != END_FRAME_NUMBER) {
            frames.add(frame);
            frame = frame.createFrame();
            return frame;
        }
        if (frame.number() == END_FRAME_NUMBER) {
            frame = frame.createFrame();
        }
        return frame;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
