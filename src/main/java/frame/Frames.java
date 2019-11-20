package frame;

import java.util.List;
import java.util.Objects;

public class Frames {

    private static final int PADDING = 1;
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
