package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.score.FrameScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final List<Frame> frames;

    Frames(Frame... frames) {
        this.frames = new ArrayList<>(Arrays.asList(frames));
    }

    int getCurrentFrameNumber() {
        return getLast().getFrameNumber();
    }

    private boolean isCurrentFrameDone() {
        return getLast().isFrameSet();
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    int getCurrentFrameShotCount() {
        return getLast().getShotsCount();
    }

    void shot(int shot) {
        frames.stream()
                .filter(Frame::isFrameSet)
                .map(Frame::getFrameScore)
                .filter(v -> !v.isCalculated())
                .forEach(v -> v.addBonus(shot));
        getLast().shot(shot);

        if (isCurrentFrameDone() && isNotFinalFrame()) {
            frames.add(getNextFrame());
        }
    }

    private boolean isNotFinalFrame() {
        return getLast().getFrameNumber() != FinalFrame.FRAME_NUMBER;
    }

    private Frame getNextFrame() {
        return getLast().next();
    }

    boolean isGameSet() {
        return getLast().isFrameSet() && !isNotFinalFrame();
    }

    public Collection<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public List<Integer> getScores() {
        return frames
                .stream()
                .map(Frame::getFrameScore)
                .filter(FrameScore::isCalculated)
                .map(FrameScore::getScore)
                .collect(Collectors.toList());
    }
}
