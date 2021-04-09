package bowling.domain.frame;

import bowling.domain.State.StateType;
import bowling.dto.FinalFrameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int MAX_HIT_COUNT = 3;

    private final List<NormalFrame> frames = new ArrayList<>();

    private final FrameNumber frameNumber;


    private FinalFrame(FrameNumber frameNumber, List<NormalFrame> frames) {
        validateFrames(frames);
        this.frameNumber = frameNumber;
        this.frames.addAll(frames);
    }

    private void validateFrames(List<NormalFrame> frames) {
        if (totalHitCount(frames) > MAX_HIT_COUNT) {
            throw new IllegalArgumentException("투구수가 너무 많습니다.");
        }
    }

    public static FinalFrame of(FrameNumber frameNumber, List<NormalFrame> frames) {
        return new FinalFrame(frameNumber, frames);
    }

    public static FinalFrame from(int frameNumber) {
        return FinalFrame.of(new FrameNumber(frameNumber), Arrays.asList(NormalFrame.first()));
    }


    public void addPinCount(int pinCount) {
        addPinCount(new PinCount(pinCount));
    }

    public void addPinCount(PinCount pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        NormalFrame currentFrame = currentFrame();
        if (currentFrame.isDone()) {
            NormalFrame next = currentFrame.next();
            frames.add(next);
            currentFrame = next;
        }
        currentFrame.addPinCount(pinCount);
    }


    private NormalFrame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public FrameNumber number() {
        return frameNumber;
    }

    @Override
    public boolean isLast() {
        return true;
    }

    public boolean isDone() {
        if (isTryAll() || isMissAtFirstFrame()) {
            return true;
        }
        return false;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임 입니다.");
    }

    private boolean isTryAll() {
        return totalHitCount(this.frames) >= MAX_HIT_COUNT;
    }

    private boolean isMissAtFirstFrame() {
        NormalFrame firstFrame = frames.get(0);
        return firstFrame.isMatchCurrentState(StateType.MISS);
    }

    private int totalHitCount(List<NormalFrame> frames) {
        return frames.stream()
                .map(NormalFrame::hitCount)
                .reduce(0, Integer::sum);
    }

    public FinalFrameResult result() {
        return new FinalFrameResult(frameNumber, frames);
    }

}
