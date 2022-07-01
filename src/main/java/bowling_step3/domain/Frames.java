package bowling_step3.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    private final List<Frame> frames;
    private int currentIndex;

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentIndex = 0;
    }

    public static Frames create() {
        Frame lastFrame = new FrameLast();
        List<Frame> frames = Stream.concat(
                Stream.of(lastFrame),
                Stream.iterate(new FrameGeneral(lastFrame), frame -> new FrameGeneral(frame)).limit(9)
        ).collect(Collectors.toList());
        Collections.reverse(frames);
        return new Frames(frames);

    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }

    public Frame first() {
        return this.frames.get(0);
    }

    public Frame get(int i) {
        return this.frames.get(i);
    }

    public List<Frame> frames() {
        return this.frames;
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

    public Frame last() {
        return get(9);
    }

    public Frame current() {
        return this.frames.get(this.currentIndex);
    }

    public void renewCurrentIndex() {
        this.currentIndex++;
    }
}
