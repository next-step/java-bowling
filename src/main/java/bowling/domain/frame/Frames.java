package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Frames {
    private final List<Frame> frames;

    public Frames() {
        this(generateFrames());
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames addScore(int score) throws Exception {
        if (isFinished()) {
            throw new IndexOutOfBoundsException();
        }
        this.frames.get(nowFrame()).addScore(score);
        return new Frames(this.frames);
    }

    public boolean isFinished() {
        if (nowFrame() > 9) {
            return true;
        }
        return false;
    }

    public int nowFrame() {
        return IntStream.range(0, 10)
                .filter(i -> !frames.get(i).isFinished())
                .findFirst()
                .orElse(10);
    }

    public List<String> toPrint() {
        List<String> result = new ArrayList<>();
        frames.forEach(frame -> result.add(frame.toPrint()));
        return result;
    }

    public Frame getFrame(int index) {
        return this.frames.get(index);
    }

    private static List<Frame> generateFrames() {
        List<Frame> result = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> result.add(new NormalFrame()));
        result.add(new FinalFrame());
        return result;
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

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
