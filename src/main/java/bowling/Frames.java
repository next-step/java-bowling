package bowling;

import bowling.domain.factory.BowlingScoresFactory;
import bowling.domain.factory.ScoresFactory;
import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;
    private final ScoresFactory scoresFactory;

    public Frames() {
        this(new ArrayList<>(), new BowlingScoresFactory());
    }

    public Frames(List<Frame> frames, ScoresFactory scoresFactory) {
        this.frames = frames;
        this.scoresFactory = scoresFactory;
    }

    public boolean lastFrameStrokeIsClosed() {
        return getLastFrame().isClosedStroke();
    }

    public Frames add(int hitCount) {
        if (lastFrameStrokeIsClosed()) {
            frames.add(new Frame(scoresFactory.create(frames.size(), hitCount)));
            // 사이즈가 1이면, 2회차에 추가.
        }

        Frame lastFrame = getLastFrame();
//        lastFrame.

        return null;
//        return new Frames()
    }

    public Frame getLastFrame() {
        if (frames.isEmpty()) {
            return new Frame(scoresFactory.create(0));
        }

        return frames.get(frames.size() - 1);
    }
}
