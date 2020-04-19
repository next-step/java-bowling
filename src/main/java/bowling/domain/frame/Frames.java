package bowling.domain.frame;

import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int FIRST_FRAME_INDEX = 0;
    private final List<Frame> frames;
    private Frame currentFrame;

    private Frames() {
        this.frames = new ArrayList<>(Arrays.asList(NormalFrame.ofFirst()));
        this.currentFrame = frames.get(FIRST_FRAME_INDEX);
    }

    public static Frames create() {
        return new Frames();
    }

    public void bowl(final Pins knockOver) {
        currentFrame.bowl(knockOver);
        if (hasNextFrame()) {
            currentFrame = currentFrame.getNext().get();
            frames.add(currentFrame);
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
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

    private boolean hasNextFrame() {
        return currentFrame.isEnd() && currentFrame.getNext().isPresent();
    }
}