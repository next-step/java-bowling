package frame;

import score.ScoreInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frame.FrameNumber.LAST_FRAME_NUMBER;

public class Frames {

    private static final int PADDING = 1;
    private static final int NOT_REAL = 999;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Integer getNowFrameNumber() {
        if (frames.isEmpty()) {
            return PADDING;
        }
        return frames.size();
    }

    public boolean reachLast() {
        return getNowFrameNumber() == LAST_FRAME_NUMBER;
    }

    public boolean isNotLast() {
        return !reachLast();
    }

    public Frame getNowFrame() {
        if (frames.isEmpty()) {
            frames.add(NormalFrame.firstNormalFrame());
        }

        int lastIndex = frames.size() - PADDING;
        Frame lastFrame = frames.get(lastIndex);

        if (lastFrame.isFull()) {
            Frame nextFrame = lastFrame.nextFrame();
            frames.add(nextFrame);
            return nextFrame;
        }

        return frames.get(lastIndex);
    }

    public void addLastFrame(LastFrame nowFrame) {
        this.frames.remove(9);
        this.frames.add(9, nowFrame);
    }

    public List<ScoreInfo> findScoreInfos(int index) {
        Frame frame = findFrame(index);
        return frame.getScoreInfos();
    }

    private Frame findFrame(int i) {
        if (frames.size() > i) {
            return frames.get(i);
        }
        return new NormalFrame(NOT_REAL, new ArrayList<>());
    }

    public LastFrame getLastFrame() {
        return frames.stream()
                .filter(frame -> frame instanceof LastFrame)
                .findFirst()
                .map(frame -> (LastFrame) frame)
                .orElse(LastFrame.init());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
