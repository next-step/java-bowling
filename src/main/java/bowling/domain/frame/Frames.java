package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.States;

public class Frames {
    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>(Arrays.asList(NormalFrame.init()));
    }

    public static Frames init() {
        return new Frames();
    }

    public void bowl(int pinCount) {
        Frame nextFrame = currentFrame().bowl(BowlingPin.of(pinCount));
        if (isNextFrame()) {
            frames.add(nextFrame);
        }
    }

    private boolean isNextFrame() {
        return currentFrame().isDone()
            && currentFrame().getNext().isPresent();
    }

    public boolean isDone() {
        return this.currentFrame().isDone();
    }

    public Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public List<States> states() {
        return frames.stream()
            .map(Frame::states)
            .collect(Collectors.toList());
    }

    public List<Score> scores() {
        Scores scores = new Scores();
        for (Frame frame : frames) {
            scores.accumulate(frame.score());
        }
        return scores.toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frames frames1 = (Frames)o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
