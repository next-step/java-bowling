package bowling.domain.frame;

import bowling.domain.state.Pins;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.subtractExact;

public final class Frames {

    private static final int FIRST_INDEX = 0;
    private static final int OPERATION_UNIT = 1;

    private final List<Frame> frames;
    private Frame current;

    public Frames() {
        this.frames = generateFrames();
        this.current = frames.get(FIRST_INDEX);
    }

    private final List<Frame> generateFrames() {
        final List<Frame> frames = IntStream.range(Frame.START_SEQUENCE, Frame.LAST_SEQUENCE)
                .mapToObj(NormalFrame::from)
                .collect(Collectors.toList());
        frames.add(FinalFrame.initialize());
        return frames;
    }

    public static final Frames initialize() {
        return new Frames();
    }

    public final int sequence() {
        return current.sequence();
    }

    public final boolean isFinish() {
        return current.isFinish();
    }

    public final void bowl(final Pins pins) {
        current = current.bowl(pins);
        frames.set(sequenceToIndex(current), current);
    }

    private final int sequenceToIndex(final Frame frame) {
        return subtractExact(frame.sequence(), OPERATION_UNIT);
    }

}
