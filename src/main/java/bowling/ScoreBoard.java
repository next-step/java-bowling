package bowling;

import bowling.domain.factory.BowlingScoresFactory;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Frame> frames;

    public ScoreBoard() {
        this(new ArrayList<>());
    }

    public ScoreBoard(List<Frame> frames) {
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
