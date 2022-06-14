package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.NotCreateFrameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initialize() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initialize());
        return new Frames(frames);
    }

    public void bowling(int hit) {
        Frame frame = currentFrame();
        frame.bowling(hit);
        if (frame.isFinish()) {
            next(frame);
        }
    }

    private void next(Frame frame) {
        try {
            addNextFrame(frame.next());
        } catch (NotCreateFrameException e) { }
    }

    private void addNextFrame(Frame frame) {
        frames.add(frame);
    }

    public Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isFinish() {
        return currentFrame().isFinish();
    }

    public boolean isProgressing() {
        return currentFrame().isProgressing();
    }

    public List<Frame> toList() {
        return Collections.unmodifiableList(frames);
    }
}
