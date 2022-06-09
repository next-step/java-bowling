package bowling_step3.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        return new Frames(Stream.iterate(0, i -> i < 10, i -> ++i)
                .map(i -> createFrame(i))
                .collect(Collectors.toList()));
    }

    private static Frame createFrame(Integer index) {
        return index < 9 ? new FrameGeneral() : new FrameLast();
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

    public Frame first() {
        return this.frames.get(0);
    }

    public Frame next(Frame frame) {
        return this.frames.get(index(frame) + 1);
    }

    int index(Frame frame) {
        return this.frames.indexOf(frame);
    }

    public Frame prev(Frame frame) {
        if (index(frame) == 0) {
            throw new UnsupportedOperationException("cannot access to prev");
        }
        return this.frames.get(index(frame) - 1);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }

    public Frame get(int i) {
        return this.frames.get(i);
    }

    public List<Frame> frames() {
        return this.frames;
    }
}
