package bowling.domain.frame;

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
    private Count count;

    private Frames(final List<Frame> frames, final Count count) {
        this.frames = Collections.unmodifiableList(frames);
        this.count = count;
    }

    public static Frames create() {
        List<Frame> frames = new ArrayList<>();
        FinalFrame finalFrame = new FinalFrame(new FrameNumber(FrameNumber.MAX_NUMBER));
        frames.add(finalFrame);

        Frame prevFrame = finalFrame;
        for (int i = NORMAL_FRAME_COUNT; i > 0; i--) {
            final Frame normalFrame = new NormalFrame(new FrameNumber(i), prevFrame);
            frames.add(normalFrame);
            prevFrame = normalFrame;
        }

        Collections.reverse(frames);
        return new Frames(frames, Count.ofFirst());
    }

    public void bowl(final Pins knockOver) {
        Frame current = getCurrent();
        current.bowl(knockOver);
        if (current.isEnd()) {
            count = count.increaseNormalFrameCount();
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

    public Frame getCurrent() {
        return frames.get(count.getCount());
    }

    public List<Score> getScores() {
        Scores scores = new Scores();
        for (Frame frame : frames) {
            scores.accumulate(frame);
        }
        return scores.getScores();
    }
}