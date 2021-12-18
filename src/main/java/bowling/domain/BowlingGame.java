package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {
    private final List<Frame> frames = new ArrayList<>();

    private BowlingGame() {
    }

    public static BowlingGame init() {
        return new BowlingGame();
    }

    public List<Frame> run(PitchNumberStrategy numberStrategy) {
        Frame frame = NormalFrame.first();
        while (progressing(frame)) {
            validateFrameCreate(frame);
            frame = run(frame, numberStrategy);
        }
        return Collections.unmodifiableList(frames);
    }

    private Frame run(Frame frame, PitchNumberStrategy numberStrategy) {
        frame.run(numberStrategy);
        frames.add(frame);
        if (frame.isFinal()) {
            return frame;
        }
        return frame.next();
    }

    private boolean progressing(Frame frame) {
        return frame.progressing();
    }

    private void validateFrameCreate(Frame frame) {
        assert frame != null;
    }
}
