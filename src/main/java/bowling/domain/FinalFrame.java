package bowling.domain;

import bowling.dto.FinalFrameResult;
import bowling.dto.FrameScoreResult;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame {

    private static final int MAX_TRY_COUNT = 3;

    private final List<Frame> frames;

    private final FrameNumber frameNumber;

    private int currentFrameIdx;

    private FinalFrame(List<Frame> frames, FrameNumber frameNumber) {
        validateFramesTryCount(frames);
        this.frames = frames;
        this.frameNumber = frameNumber;
    }

    private void validateFramesTryCount(List<Frame> frames) {
        int totalTryCount = frames.stream()
                .map(Frame::tryCount)
                .reduce(0, Integer::sum);

        if(totalTryCount > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("bonusframe 숫자가 너무 많습니다.");
        }
    }

    public static FinalFrame from(List<Frame> frames, FrameNumber frameNumber) {
        return new FinalFrame(frames,frameNumber);
    }

    public static FinalFrame of(int frameNumber) {
        List<Frame> frames = new ArrayList<>();
        frames.add(new Frame());
        return new FinalFrame(frames, new FrameNumber(frameNumber));
    }

    public void addPinCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        addBonusFrameIfNecessary();
        Frame currentFrame = frames.get(currentFrameIdx);
        currentFrame.addPinCount(pinCount);
    }

    private void addBonusFrameIfNecessary() {
        Frame currentFrame = frames.get(currentFrameIdx);
        if (currentFrame.isDone() && currentFrameIdx < MAX_TRY_COUNT - 1) {
            frames.add(new Frame());
            currentFrameIdx++;
        }
    }

    public FrameNumber number() {
        return frameNumber;
    }

    public boolean isDone() {
        if (isTryAll()) {
            return true;
        }

        if (isFirstFrameMiss()) {
            return true;
        }

        return false;
    }

    private boolean isTryAll() {
        int totalTryCount = frames.stream()
                .map(Frame::tryCount)
                .reduce(0, Integer::sum);

        return totalTryCount >= MAX_TRY_COUNT;
    }

    private boolean isFirstFrameMiss() {
        Frame firstBonusFrame = frames.get(0);
        return firstBonusFrame.isMatch(FrameScoreResult.MISS);
    }

    public FinalFrameResult result() {
        return new FinalFrameResult(frameNumber, frames);
    }


}
