package bowling;

import bowling.domain.factory.BowlingScoresFactory;
import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        this(new ArrayList<>());
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public boolean lastFrameStrokeIsClosed() {
        return getLastFrame().isClosedStroke();
    }

    public Frame getLastFrame() {
        if (frames.isEmpty()) {
            return new Frame(0, new BowlingScoresFactory());
        }

        return frames.get(frames.size() - 1);
    }
}
