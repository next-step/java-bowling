package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initialize() {
        return new Frames(makeFrames());
    }

    private static List<Frame> makeFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.createFirstFrame());
        for (int i = 1; i < RoundNumber.MAX; i++) {
            frames.add(frames.get(i - 1).createNextFrame());
        }
        return frames;
    }

    public List<Frame> value() {
        return frames;
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
