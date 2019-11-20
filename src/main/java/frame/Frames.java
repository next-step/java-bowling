package frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frames {

    private static final int PADDING = 1;
    private static final int NEXT_NUMBER = 2;
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Integer getNextFrameNumber() {
        if (frames.isEmpty()) {
            return PADDING;
        }
        return frames.size() + PADDING;
    }

    public Frame getNowFrame() {
        if (frames.isEmpty()) {
            frames.add(new NormalFrame(1, new ArrayList<>()));
            return frames.get(0);
        }

        int lastIndex = frames.size() - 1;
        Frame lastFrame = frames.get(lastIndex);

        if (lastFrame.isFull()) {
            return new NormalFrame(lastIndex + NEXT_NUMBER, new ArrayList<>());
        }

        return frames.get(lastIndex);
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
