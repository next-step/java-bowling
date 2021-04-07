package bowling.domain;

import bowling.dto.FinalFrameResult;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int MAX_TRY_COUNT = 3;

    private static final int MAX_TOTAL_PIN_COUNTS_PER_FRAME = 10;

    private final List<PinCount> pinCounts = new ArrayList<>();

    private final FrameNumber frameNumber;

    private Frame nextFrame;

    private final Frame previousFrame;

    private FinalFrame(FrameNumber frameNumber, List<PinCount> pinCounts, Frame previousFrame, Frame nextFrame) {
        this.frameNumber = frameNumber;
        initializePinCounts(pinCounts);
        this.previousFrame = previousFrame;
        this.nextFrame = nextFrame;
    }

    private void initializePinCounts(List<PinCount> pinCounts) {
        try {
            pinCounts.forEach(this::addPinCount);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new IllegalArgumentException("유효하지 않는 투구 값들 입니다.");
        }
    }

    private void validateToAddPinCount(PinCount pinCount) {
        PinCount lastPinCount = new PinCount(0);
        if (!pinCounts.isEmpty()) {
            lastPinCount = pinCounts.get(pinCounts.size() - 1);
        }
        if (FrameScoreResult.of(lastPinCount.count(), 1) == FrameScoreResult.NONE
                && lastPinCount.sumCount(pinCount) > MAX_TOTAL_PIN_COUNTS_PER_FRAME) {
            throw new IllegalArgumentException("추가 할 수 없는 투구입니다.");
        }
    }

    public static FinalFrame of(FrameNumber frameNumber, List<PinCount> pinCounts, Frame previousFrame, Frame nextFrame) {
        return new FinalFrame(frameNumber, pinCounts, previousFrame, nextFrame);
    }


    public void addPinCount(int pinCount) {
        addPinCount(new PinCount(pinCount));
    }

    public void addPinCount(PinCount pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        validateToAddPinCount(pinCount);
        pinCounts.add(pinCount);
    }

    public FrameNumber number() {
        return frameNumber;
    }

    public boolean isDone() {
        if (isTryAll() || isMissAtSecondTry()) {
            return true;
        }
        return false;
    }

    @Override
    public Frame nextFrame() {
        throw new IllegalStateException("마지막 프레임 이빈다.");
    }

    private boolean isTryAll() {
        return pinCounts.size() >= MAX_TRY_COUNT;
    }

    private boolean isMissAtSecondTry() {
        if (pinCounts.size() == 2) {
            PinCount firstPinCount = pinCounts.get(0);
            PinCount secondPinCount = pinCounts.get(1);
            FrameScoreResult result = FrameScoreResult.of(firstPinCount.sumCount(secondPinCount), 2);
            return result == FrameScoreResult.MISS;
        }
        return false;
    }

    public FinalFrameResult result() {
        return new FinalFrameResult(frameNumber, null);
    }


}
