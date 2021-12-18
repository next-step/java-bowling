package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {

    private BowlingGame() {
    }

    public static BowlingGame init() {
        return new BowlingGame();
    }

    public List<Frame> run(PitchNumberStrategy numberStrategy) {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.first();
        while (progressing(frame)) {
            validateFrameCreate(frame);
            frame.run(numberStrategy);
            frames.add(frame);
            frame = next(frame);
        }
        return Collections.unmodifiableList(frames);
    }

    private Frame next(Frame frame) {
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
