package bowling.domain.frame;

import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    public static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;
    private Count count;

    private Frames(final List<Frame> frames, final Count count) {
        this.frames = Collections.unmodifiableList(frames);
        this.count = count;
    }

    public static Frames create() {
        final List<Frame> frames = IntStream.rangeClosed(FrameNumber.MIN_NUMBER, NORMAL_FRAME_COUNT)
                                            .mapToObj(FrameNumber::new)
                                            .map(NormalFrame::new)
                                            .collect(Collectors.toList());
        frames.add(new FinalFrame(new FrameNumber(FrameNumber.MAX_NUMBER)));
        return new Frames(frames, Count.ofFirst());
    }

    public void bowl(final Pins knockOver) {
        Frame current = getCurrent();
        current.bowl(knockOver);
        if (current.isEnd()) {
            count = count.increaseNormalFrameCount();
        }
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<States> getStates() {
        return frames.stream()
                     .map(Frame::getStates)
                     .collect(Collectors.toList());
    }

    public Frame getCurrent() {
        return frames.get(count.getCount());
    }
}