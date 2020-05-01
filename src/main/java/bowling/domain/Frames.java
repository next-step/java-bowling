package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.score.FrameScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MAX_FRAMES = 10;

    private final List<Frame> frames;

    Frames() {
        this.frames = new ArrayList<>();
        frames.add(NormalFrame.init());
    }

    int getCurrentFrameNumber() {
        if (isCurrentFrameDone()) {
            return frames.size() + 1;
        }
        return frames.size();
    }

    private boolean isCurrentFrameDone() {
        return getLast().isFrameSet();
    }

    private Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    int getCurrentFrameShotCount() {
        if (isCurrentFrameDone()) {
            return 0;
        }
        return getLast().shots().size();
    }

    void shot(int shot) {
        if (isCurrentFrameDone()) {
            frames.add(getNextFrame());
        }
        frames.stream()
                .filter(Frame::isFrameSet)
                .map(Frame::getFrameScore)
                .filter(v -> !v.isCalculated())
                .forEach(v -> v.addBonus(shot));
        getLast().shot(shot);
    }

    private Frame getNextFrame() {
        if (frames.size() < MAX_FRAMES - 1) {
            return getLast().next();
        }
        return getLast().last();
    }

    boolean isGameSet() {
        return frames.stream()
                .filter(Frame::isFrameSet)
                .count() == MAX_FRAMES;
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
