package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.util.BowlingFixture.FRAME_LAST_INDEX;
import static bowling.util.BowlingFixture.FRAME_START_INDEX;
import static java.util.Collections.unmodifiableList;

public final class Frames {

    private static final int FIRST_INDEX = 0;

    private final List<Frame> frames;
    private Frame now;

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

    public final int index() {
        return now.index();
    }

    public final boolean isFinish() {
        return now.isFinish();
    }

    public final void bowl(HitCount hitCount) {
        validateFinish();
        now = now.bowl(hitCount);
    }

    private final void validateFinish() {
        if (isFinish()) {
            throw new NoMoreBowlActionsException();
        }
    }

    public final List<Frame> frames() {
        return unmodifiableList(frames);
    }
}
