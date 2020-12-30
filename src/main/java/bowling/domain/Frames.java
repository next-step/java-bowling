package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    public static final int NUMBER_OF_FRAMES = 10;
    public static final int FINAL_FRAME_CONDITION = 9;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>(NUMBER_OF_FRAMES);
        return new Frames(frames);
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public Frames add(Frame frame) {
        frames.add(frame);
        return new Frames(frames);
    }

    public int getSize() {
        return frames.size();
    }

    public boolean isFinished() {
        return frames.size() == NUMBER_OF_FRAMES;
    }

    public boolean isFinalFrame() {
        return frames.size() >= FINAL_FRAME_CONDITION;
    }

    public Frame createFrame() {
        if(isFinalFrame()) {
            return FinalFrame.init();
        }
        return NormalFrame.init();
    }
}
