package bowling.domain;

import bowling.dto.FrameScoreResult;
import bowling.dto.NormalFrameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame {

    private static final int MAX_TRY_COUNT = 2;

    private static final int MAX_TOTAL_PIN_COUNTS = 10;

    private final List<PinCount> pinCounts;

    private final FrameNumber frameNumber;

    private NormalFrame(FrameNumber frameNumber, List<PinCount> pinCounts) {
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

    private FrameScoreResult scoreResult() {
        if (totalPinCounts(this.pinCounts) == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT - 1) {
            return FrameScoreResult.STRIKE;
        }

        if (totalPinCounts(this.pinCounts) == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT) {
            return FrameScoreResult.SPARE;
        }

        if (totalPinCounts(this.pinCounts) < MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT) {
            return FrameScoreResult.MISS;
        }

        return FrameScoreResult.NONE;
    }

    public NormalFrame(int frameNumber) {
        this(new FrameNumber(frameNumber), new ArrayList<>());
    }

    public NormalFrame(FrameNumber frameNumber) {
        this(frameNumber, new ArrayList<>());
    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first());
    }

    public static NormalFrame from(FrameNumber frameNumber, List<PinCount> pinCounts) {
        return new NormalFrame(frameNumber, pinCounts);
    }

    public NormalFrame next() {
        return new NormalFrame(frameNumber.next());
    }

    public void addPinCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        validateTotalPinCounts(totalPinCounts(this.pinCounts) + pinCount);
        pinCounts.add(new PinCount(pinCount));
    }

    private int totalPinCounts(List<PinCount> pinCounts) {
        return pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);
    }

    public boolean isDone() {
        return pinCounts.size() >= MAX_TRY_COUNT || totalPinCounts() >= MAX_TOTAL_PIN_COUNTS;
    }

    public NormalFrameResult result() {
        return new NormalFrameResult(frameNumber, pinCounts);
    }

    public FrameNumber number() {
        return frameNumber;
    }
}
