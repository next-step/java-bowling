package bowling.domain;

import bowling.domain.frameScore.FrameScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int MAX_FRAMES = 10;

    private final List<NormalFrame> normalFrames;

    Frames() {
        this.normalFrames = new ArrayList<>();
        normalFrames.add(NormalFrame.normalFrame());
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

    private NormalFrame getLast() {
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
                .filter(v -> !v.isScoreCalculated())
                .forEach(v -> v.shot(shot));
    }

    private NormalFrame getNextFrame() {
        if (normalFrames.size() < MAX_FRAMES - 1) {
            return NormalFrame.normalFrame();
        }
        return NormalFrame.lastFrame();
    }

    boolean isGameSet() {
        return normalFrames.stream()
                .filter(NormalFrame::isFrameSet)
                .count() == MAX_FRAMES;
    }

    public Collection<NormalFrame> getNormalFrames() {
        return new ArrayList<>(normalFrames);
    }

    public List<Integer> getScores() {
        return normalFrames
                .stream()
                .map(NormalFrame::getFrameScore)
                .filter(FrameScore::isCalculated)
                .map(FrameScore::getScore)
                .collect(Collectors.toList());
    }
}
