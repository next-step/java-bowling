package refactor;

import java.util.Arrays;
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
        return new Frames(Stream.iterate(new Frame(), frame -> new Frame())
                .limit(10)
                .collect(Collectors.toList()));
    }

    public void save(Frame frame) {
       this.frames.set(this.frames.indexOf(frame), frame);
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
        return this.frames.get(this.frames.indexOf(frame) + 1);
    }

//    public Frame play(Frame frame) {
//        Frame doneFrame = frame.pitches();
//        int index = this.frames.indexOf(frame);
//        this.frames.set(index, doneFrame);
//        Frame accumulatedNextFrame = doneFrame.accumulatedNextFrame(next(doneFrame));
//        this.frames.set(index + 1, accumulatedNextFrame);
//        return doneFrame;

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
//    }
}
