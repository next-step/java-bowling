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

    private final List<Frame> normalFrames;

    Frames() {
        this.normalFrames = new ArrayList<>();
        normalFrames.add(NormalFrame.init());
    }

    int getCurrentFrameNumber() {
        if (isCurrentFrameDone()) {
            return normalFrames.size() + 1;
        }
        return normalFrames.size();
    }

    private boolean isCurrentFrameDone() {
        return getLast().isFrameSet();
    }

    private Frame getLast() {
        return normalFrames.get(normalFrames.size() - 1);
    }

    int getCurrentFrameShotCount() {
        if (isCurrentFrameDone()) {
            return 0;
        }
        return getLast().shotScores().size();
    }

    void shot(int shot) {
        if (isCurrentFrameDone()) {
            normalFrames.add(getNextFrame());
        }
        normalFrames.stream()
                .map(Frame::getFrameScore)
                .filter(v -> !v.isCalculated())
                .forEach(v -> v.addBonus(shot));
        getLast().shot(shot);
    }

    private Frame getNextFrame() {
        if (normalFrames.size() < MAX_FRAMES - 1) {
            return getLast().next();
        }
        return getLast().last();
    }

    boolean isGameSet() {
        return normalFrames.stream()
                .filter(Frame::isFrameSet)
                .count() == MAX_FRAMES;
    }

    public Collection<Frame> getNormalFrames() {
        return new ArrayList<>(normalFrames);
    }

    public List<Integer> getScores() {
        return normalFrames
                .stream()
                .map(Frame::getFrameScore)
                .filter(FrameScore::isCalculated)
                .map(FrameScore::getScore)
                .collect(Collectors.toList());
    }
}
