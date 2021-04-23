package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    private static final int FINAL_FRAME_INDEX = 9;
    private static final int FIRST_FRAME_INDEX = 0;

    private final List<Frame> frames;

    public Frames() {
        this(init());
    }

    private static List<Frame> init() {
        List<Frame> frames = IntStream.range(0, FINAL_FRAME_INDEX)
                .mapToObj(NormalFrame::new)
                .collect(Collectors.toList());

        frames.add(new FinalFrame());

        return frames;
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frame first() {
        return get(FIRST_FRAME_INDEX);
    }

    public boolean isAllFinished() {
        return frames.stream()
                .allMatch(Frame::isFinished);
    }

    public Frame get(int index) {
        return frames.get(index);
    }

    public void update(Frame frame) {
        frames.set(frame.index(), frame);
    }

    public List<Frame> list() {
        return Collections.unmodifiableList(frames);
    }
}
