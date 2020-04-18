package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pitch.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frames {
    public static final int DEFAULT_FRAME_SIZE = 10;
    private static final int ONE = 1;

    private List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public Frames(Frames frames) {
        this.frames = new ArrayList<>(frames.frames);
    }

    public Optional<Integer> getFrameScore(int frameIndex) {
        return frames.get(frameIndex).getScore();
    }

    public List<Pitch> getFramePinCounts(int index) {
        return frames.get(index).getPitches();
    }

    public void addPinCount(int pinCount) {
        getLastFrame().addPinCount(pinCount);
        if (isAddable()) {
            frames.add(createNext());
        }
    }

    public int size() {
        return frames.size();
    }

    private Frame getLastFrame() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - ONE;
    }

    private boolean isBeforeMax() {
        return frames.size() == (DEFAULT_FRAME_SIZE - ONE);
    }

    public Frame createNext() {
        Frame lastFrame = getLastFrame();
        if (isBeforeMax()) {
            return lastFrame.createNext(new FinalFrame());
        }

        return lastFrame.createNext();
    }

    private boolean isAddable() {
        if (isFinished()) {
            return false;
        }
        Frame lastFrame = getLastFrame();
        return lastFrame.isDone();
    }

    public boolean isFinished() {
        Frame lastFrame = getLastFrame();
        return lastFrame.isLast() && lastFrame.isDone();
    }
}
