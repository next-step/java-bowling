package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Frames {
    private final List<Frame> frames;

    public Frames() {
        this(generateFrames());
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public boolean isFinished() {
        if (nowFrameIndex() > 9) {
            return true;
        }
        return false;
    }

    public int nextFrame() {
        if (frames.isEmpty()) {
            return 0;
        }
        if (frames.get(nowFrameIndex() - 1).isFinished()) {
            return nowFrameIndex() + 1;
        }
        return nowFrameIndex();
    }

    public Frames addScore(int score) throws Exception {
        if (nowFrameIndex() == nextFrame()) {
            frames.get(nowFrameIndex()).addScore(score);
        }
        if (nextFrame() == 9) {
            frames.get(nextFrame()).addScore(score);
        }
        return new Frames(this.frames);
    }

    private int nowFrameIndex() {
        return IntStream.range(0, 10)
                .filter(i -> !frames.get(i).isFinished())
                .findFirst()
                .orElse(10);
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
