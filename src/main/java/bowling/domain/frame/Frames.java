package bowling.domain.frame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Frames {

    private static final int FIRST_FRAME = 0;

    private final List<Frame> frames;
    private Frame current;

    public Frames() {
        this.frames = generateFrames();
        this.current = frames.get(FIRST_FRAME);
    }

    private final List<Frame> generateFrames() {
        final List<Frame> frames = IntStream.range(Frame.START_SEQUENCE, Frame.LAST_SEQUENCE)
                .mapToObj(NormalFrame::from)
                .collect(Collectors.toList());
        frames.add(FinalFrame.initialize());
        return frames;
    }

    public static Frames initialize() {
        return new Frames();
    }

    public final int sequence() {
        return current.sequence();
    }

    public final boolean isFinish() {
        return current.isFinish();
    }
}
