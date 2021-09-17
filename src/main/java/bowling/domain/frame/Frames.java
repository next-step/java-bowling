package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_INDEX = 0;

    private static final int START_FRAME_ROUND = 1;
    private static final int FINAL_FRAME_ROUND = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.createFirstFrame();
        frames.add(frame);
        IntStream.range(START_FRAME_ROUND, FINAL_FRAME_ROUND)
            .mapToObj(index -> frame.createNextFrame())
            .forEach(frames::add);
        return new Frames(frames);
    }

    public static Frames creatByFirstFrame(Frame frame) {
        List<Frame> frames = new ArrayList<>();
        while (Objects.nonNull(frame)) {
            frames.add(frame);
            frame = frame.nextFrame();
        }
        return new Frames(frames);
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(frames);
    }

    public Frame first() {
        return frames.get(START_INDEX);
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
