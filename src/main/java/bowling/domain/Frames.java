package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    private static final int FINAL_FRAME_INDEX = 9;
    private static final int FIRST_INDEX = 0;

    private final List<Frame> frames;
    private int currentIndex;

    public Frames() {
        this(init());
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentIndex = FIRST_INDEX;
    }

    private static List<Frame> init() {
        List<Frame> frames = IntStream.range(0, FINAL_FRAME_INDEX)
                .mapToObj(NormalFrame::new)
                .collect(Collectors.toList());

        frames.add(new FinalFrame());

        return frames;
    }

    public int currentIndex() {
        return currentIndex;
    }

    public void throwBowl(String pinCount) {
        Frame Frame = get(currentIndex);
        Frame = Frame.throwBowl(pinCount);
        update(Frame);
        Frame = Frame.next();
        currentIndex = Frame.index();
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

    public List<FrameResult> results() {
        return frames.stream()
                .map(FrameResult::new)
                .collect(Collectors.toList());
    }
}
