package bowling.domain;

import aTest.Frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        add(frame);
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public Frame getCurrentFrame() {
        return this.frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
