package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {
    private static final int FIRST_FRAME_ID = 1;
    private static final int FINAL_FRAME_ID = 10;
    private static final int MAX_POINT = 10;
    private static final int DOUBLE = 2;

    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getFrameId(int index) {
        return frames.get(index).getFrameId();
    }

    public int getFrameScore(int frameId) {
        Frame frame = getPrevFrameByCurrentFrameId(frameId);

        if (frameId == FINAL_FRAME_ID) {
            return frame.getPointSumOnlyThisFrame();
        }

        if (frame.isStrike()) {
            return findFrameScoreWhenStrike(frame);
        }

        if (frame.isSpare()) {
            int firstPointOfNextFrame = getNextFrameByCurrentId(frameId).getFirstPoint();
            return (frame.getPointSumOnlyThisFrame() + firstPointOfNextFrame);
        }

        return frame.getPointSumOnlyThisFrame();
    }

    public int getTotalPointUntil(Frame currentFrame) {
        return IntStream.rangeClosed(FIRST_FRAME_ID, currentFrame.getFrameId())
                .map(it -> Frames.of(frames).getFrameScore(it))
                .sum();
    }

    public Frame getPreviousFrame(Frame currentFrame) {
        if (currentFrame.getFrameId() != FIRST_FRAME_ID) {
            return getPrevFrameByCurrentFrameId(currentFrame.getFrameId());
        }

        return currentFrame;
    }

    private Frame getNextFrameByCurrentId(int currentFrameId) {
        return frames.get(currentFrameId);
    }

    private int findFrameScoreWhenStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (nextFrame.isStrike()) {
            return findFrameScoreWhenCurrentAndNextAreStrike(currentFrame);
        }

        return (MAX_POINT + nextFrame.getPointSumOnlyThisFrame());
    }

    private int findFrameScoreWhenCurrentAndNextAreStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (currentFrame.isNineth()) {
            return (currentFrame.getFirstPoint() + nextFrame.getFirstPoint() + nextFrame.getThirdPoint());
        }

        int firstOfNextOfNext = getNextFrameByCurrentId(nextFrame.getFrameId()).getFirstPoint();
        return (MAX_POINT * DOUBLE) + firstOfNextOfNext;
    }

    private Frame getPrevFrameByCurrentFrameId(int currentId) {
        return frames.get(currentId - FIRST_FRAME_ID);
    }
}