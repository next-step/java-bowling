package bowling.domain.frame;

import bowling.controller.BowlingGame;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    public static final int NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;
    private Frame currentFrame;

    private Frames(final List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
        this.currentFrame = frames.get(0);
    }

    public static Frames create() {
        List<Frame> frames = new ArrayList<>();
        FinalFrame finalFrame = new FinalFrame(FrameNumber.valueOf(FrameNumber.MAX_NUMBER));
        frames.add(finalFrame);

        Frame prevFrame = finalFrame;
        for (int i = NORMAL_FRAME_COUNT; i > 0; i--) {
            final Frame normalFrame = new NormalFrame(FrameNumber.valueOf(i), prevFrame);
            frames.add(normalFrame);
            prevFrame = normalFrame;
        }

        Collections.reverse(frames);
        return new Frames(frames);
    }

    public void bowl(final Pins knockOver) {
        currentFrame.bowl(knockOver);
        if (isPossibleNextFrame()) {
            currentFrame = currentFrame.getNext()
                                       .get();
        }
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public List<States> getStates() {
        return frames.stream()
                     .map(Frame::getStates)
                     .collect(Collectors.toList());
    }

    public List<Score> getScores() {
        Scores scores = new Scores();
        for (Frame frame : frames) {
            scores.accumulate(frame);
        }
        return scores.getScores();
    }

    public Frame getCurrent() {
        return currentFrame;
    }

    private boolean isPossibleNextFrame() {
        FrameNumber frameNumber = currentFrame.getFrameNumber();
        return currentFrame.isEnd() && !frameNumber.isFinal();
    }
}