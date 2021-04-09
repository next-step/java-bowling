package bowling.domain.frame;

import bowling.domain.State.StateType;
import bowling.dto.FinalFrameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int MAX_HIT_COUNT = 3;

//    private static final int MAX_TOTAL_PIN_COUNTS_PER_FRAME = 10;
//
//    private final List<PinCount> pinCounts = new ArrayList<>();

    private final List<NormalFrame> frames = new ArrayList<>();

    private final FrameNumber frameNumber;

//    private Frame nextFrame;

    private FinalFrame(FrameNumber frameNumber, List<NormalFrame> frames) {
        validateFrames(frames);
        this.frameNumber = frameNumber;
        this.frames.addAll(frames);
        // this.nextFrame = nextFrame;
    }

    private void validateFrames(List<NormalFrame> frames) {
        if (totalHitCount(frames) > MAX_HIT_COUNT) {
            throw new IllegalArgumentException("투구수가 너무 많습니다.");
        }
    }

//    private void initializePinCounts(List<PinCount> pinCounts) {
//        try {
//            pinCounts.forEach(this::addPinCount);
//        } catch (IllegalArgumentException | IllegalStateException exception) {
//            throw new IllegalArgumentException("유효하지 않는 투구 값들 입니다.");
//        }
//    }
//
//    private void validateToAddPinCount(PinCount pinCount) {
//        PinCount lastPinCount = new PinCount(0);
//        if (!pinCounts.isEmpty()) {
//            lastPinCount = pinCounts.get(pinCounts.size() - 1);
//        }
//        if (FrameScoreResult.of(lastPinCount.count(), 1) == FrameScoreResult.NONE
//                && lastPinCount.sumCount(pinCount) > MAX_TOTAL_PIN_COUNTS_PER_FRAME) {
//            throw new IllegalArgumentException("추가 할 수 없는 투구입니다.");
//        }
//    }

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
//        if (isDone()) {
//            throw new IllegalStateException("이미 끝난 프레임 입니다.");
//        }
//        validateToAddPinCount(pinCount);
//        pinCounts.add(pinCount);

        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        NormalFrame currentFrame = currentFrame();
        currentFrame.addPinCount(pinCount);
        addFrameIfNecessary(currentFrame);
    }

    private void addFrameIfNecessary(NormalFrame currentFrame) {
        if (frames.size() < MAX_HIT_COUNT && currentFrame.isDone()) {
            frames.add(currentFrame.next());
        }
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
//        if (pinCounts.size() == 2) {
//            PinCount firstPinCount = pinCounts.get(0);
//            PinCount secondPinCount = pinCounts.get(1);
//            FrameStateType result = FrameStateType.of(firstPinCount.sumCount(secondPinCount), 2);
//            return result == FrameStateType.MISS;
//        }
//        return false;
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
