package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames createFramesByFirstFrame(Frame frame) {
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);
        while (frame instanceof NormalFrame && Objects.nonNull(frame.nextFrame())) {
            frame = frame.nextFrame();
            frames.add(frame);
        }
        return new Frames(frames);
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

}
