package frame;

import score.ScoreInfo;
import score.framescore.FrameScore;

import java.util.ArrayList;
import java.util.List;

import static frame.info.FrameNumber.LAST_FRAME_NUMBER;

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
            Frame nextFrame = getNextFrame(lastFrame);
            frames.add(nextFrame);
            return nextFrame;
        }

        return frames.get(lastIndex);
    }

    private Frame getNextFrame(Frame lastFrame) {
        Frame nextFrame = lastFrame.nextFrame();
        if (nextFrame.getFrameNumber() == LAST_FRAME_NUMBER) {
            return lastFrame.getLastFrame();
        }
        return nextFrame;
    }

    public List<ScoreInfo> findScoreInfos(int index) {
        return findFrame(index).getScoreInfos();
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

    public int size() {
        return this.frames.size();
    }

    public FrameScore getFrameScore(int index) {
        return this.frames.get(index)
                .getFrameScore();
    }
}
