package frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private static final int PADDING = 1;
    private static final int NEXT_NUMBER = 2;
    private static final int NOT_REAL = 999;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Integer getNowFrameNumber() {
        if (frames.isEmpty()) {
            return PADDING;
        }
        return frames.size();
    }

    public Frame getNowFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.firstNormalFrame());
        }

        int lastIndex = frames.size() - PADDING;
        Frame lastFrame = frames.get(lastIndex);

        if (lastFrame.isFull()) {
            NormalFrame nextFrame = new NormalFrame(lastIndex + NEXT_NUMBER, new ArrayList<>());
            frames.add(nextFrame);
            return nextFrame;
        }

        return frames.get(lastIndex);
    }

    public void add(int index, Frame nowFrame) {
        this.frames.remove(index);
        this.frames.add(index, nowFrame);
    }

    public Frame findFrame(int i) {
        if (frames.size() > i) {
            return frames.get(i);
        }
        return new NormalFrame(NOT_REAL, new ArrayList<>());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
