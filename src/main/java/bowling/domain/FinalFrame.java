package bowling.domain;

import bowling.dto.FinalFrameResult;
import bowling.dto.FrameScoreResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame {

    private static final int MAX_TRY_COUNT = 3;

    private static final int MAX_TOTAL_PIN_COUNTS = 30;

    private final List<PinCount> pinCounts;

    private final FrameNumber frameNumber;

    private int currentFrameIdx;

    private FinalFrame(FrameNumber frameNumber, List<PinCount> pinCounts) {
        validatePinCountsSize(pinCounts.size());
        validateTotalPinCounts(totalPinCounts(pinCounts));
        this.frameNumber = frameNumber;
        this.pinCounts = pinCounts.stream()
                .map(pinCount -> new PinCount(pinCount.count()))
                .collect(Collectors.toList());
    }

    private void validatePinCountsSize(int size) {
        if (size > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("투구 수가 너무 많습니다.");
        }
    }

    private void validateTotalPinCounts(int totalCount) {
        if (totalCount > MAX_TOTAL_PIN_COUNTS) {
            throw new IllegalArgumentException("투구 결과 핀수가 너무 많습니다.");
        }

    }

    private int totalPinCounts(List<PinCount> pinCounts) {
        return pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);
    }

    public static FinalFrame from(FrameNumber frameNumber, List<PinCount> pinCounts) {
        return new FinalFrame(frameNumber, pinCounts);
    }

    public static FinalFrame of(int frameNumber) {
        return new FinalFrame(new FrameNumber(frameNumber), new ArrayList<>());
    }

    public void addPinCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        addBonusFrameIfNecessary();
        pinCounts.add(new PinCount(pinCount));
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
        return totalPinCounts(this.pinCounts) >= MAX_TRY_COUNT;
    }

    private boolean isFirstFrameMiss() {
        Frame firstBonusFrame = frames.get(0);
        return firstBonusFrame.isMatch(FrameScoreResult.MISS);
    }

    public FinalFrameResult result() {
        return new FinalFrameResult(frameNumber, frames);
    }


}
