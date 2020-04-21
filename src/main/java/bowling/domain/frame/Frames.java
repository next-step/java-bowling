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
    private static final int LAST_INDEX = 1;

    private final List<Frame> frames;
    private Frame cursor;

    private Frames() {
        this.frames = new ArrayList<>(Arrays.asList(NormalFrame.ofFirst()));
        this.cursor = frames.get(FIRST_FRAME_INDEX);
    }

    public static Frames create() {
        return new Frames();
    }

    public void bowl(final Pins knockOver) {
        final Frame afterBowlingFrame = getLast().bowl(knockOver);
        if (isNextFrame()) {
            frames.add(afterBowlingFrame);
        }
    }

    public void changeFrameToNext() {
        cursor = getLast();
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
        return cursor;
    }

    private boolean isNextFrame() {
        return getLast().isFinish() && getLast().getNext().isPresent();
    }

    private Frame getLast() {
        return frames.get(frames.size() - LAST_INDEX);
    }
}