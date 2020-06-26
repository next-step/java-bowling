package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final int FRAME_SIZE_CALCULATION = 1;
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        add(frame);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public Frame getCurrentFrame() {
        return this.frames.get(frames.size() - FRAME_SIZE_CALCULATION);
    }

    public int getCurrentFrameNumber() {
        return this.frames.indexOf(getCurrentFrame()) + FRAME_SIZE_CALCULATION;
    }

    public boolean isEndGame() {
        return getCurrentFrame().isEndGame();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
