package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.util.BowlingFixture.FRAME_LAST_INDEX;
import static bowling.util.BowlingFixture.FRAME_START_INDEX;

public class Frames {

    private static final int FIRST_INDEX = 0;

    private final List<Frame> frames;
    private final Frame now;

    private Frames(final List<Frame> frames) {
        this.frames = frames;
        this.now = frames.get(FIRST_INDEX);
    }

    public static final Frames initialize() {
        return new Frames(generateFrameList());
    }

    private static List<Frame> generateFrameList() {
        List<Frame> frameList = IntStream.range(FRAME_START_INDEX, FRAME_LAST_INDEX)
                .mapToObj(NormalFrame::from)
                .collect(Collectors.toList());
        frameList.add(FinalFrame.initialize());
        return frameList;
    }

}
