package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    private static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public static Frames create() {
        final List<Frame> frames = IntStream.rangeClosed(FrameNumber.MIN_NUMBER, NORMAL_FRAME_COUNT)
                                            .mapToObj(FrameNumber::new)
                                            .map(NormalFrame::new)
                                            .collect(Collectors.toList());
        frames.add(new FinalFrame(new FrameNumber(FrameNumber.MAX_NUMBER)));
        return new Frames(frames);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<String> getStateResult() {
        return frames.stream()
                     .map(Frame::getState)
                     .collect(Collectors.toList());
    }
}
