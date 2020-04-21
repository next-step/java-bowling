package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.frame.FrameResult.SPARE;
import static bowling.domain.frame.FrameResult.STRIKE;

public class Frames {
    private static final int FIRST_FRAME_ID = 1;
    private static final int MAX_POINT = 10;
    private static final int DOUBLE = 2;
    private static final int OFFSET = 1;

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
        Frame frame = frames.get(frameId - OFFSET);

        if (frame.isFinalFrame()) {
            return frame.getPointSumOnlyThisFrame();
        }

        if (frame.isResult(STRIKE)) {
            return findFrameScoreWhenStrike(frame);
        }

        if (frame.isResult(SPARE)) {
            int firstPointOfNextFrame = getNextFrameByCurrentId(frameId).getFirstPoint();
            return (frame.getPointSumOnlyThisFrame() + firstPointOfNextFrame);
        }

        return frame.getPointSumOnlyThisFrame();
    }

    public int getTotalPointUntil(int currentFrameId) {
        return IntStream.rangeClosed(FIRST_FRAME_ID, currentFrameId)
                .map(it -> Frames.of(frames).getFrameScore(it))
                .sum();
    }

    private Frame getNextFrameByCurrentId(int currentFrameId) {
        return frames.get(currentFrameId);
    }

    private int findFrameScoreWhenStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (nextFrame.isResult(STRIKE)) {
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
}