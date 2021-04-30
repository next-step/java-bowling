package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Frames {
    private final List<Frame> frames;
    public static final Integer FINAL_FRAME = 9;

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
        if (nowFrame() > FINAL_FRAME) {
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

    public Frame getFrame(int index) {
        return this.frames.get(index);
    }

    private static List<Frame> generateFrames() {
        List<Frame> result = new LinkedList<>();
        IntStream.range(0, FINAL_FRAME)
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
