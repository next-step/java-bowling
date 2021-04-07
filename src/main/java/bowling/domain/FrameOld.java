package bowling.domain;

import bowling.dto.FrameResult;

import java.util.ArrayList;
import java.util.List;

public class FrameOld {

    private static final int MAX_TRY_COUNT = 2;

    private static final int MAX_TOTAL_PIN_COUNTS = 10;

    private final List<PinCount> pinCounts;

    public FrameOld(List<PinCount> pinCounts) {
        Integer totalCount = pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);

        validatePinCountsSize(pinCounts.size());
        validateTotalPinCounts(totalCount);
        this.pinCounts = pinCounts;
    }

    public FrameOld() {
        this(new ArrayList<>());
    }

    private void validateTotalPinCounts(int totalCount) {
        if(totalCount > MAX_TOTAL_PIN_COUNTS) {
            throw new IllegalArgumentException("투구 결과 핀수가 너무 많습니다.");
        }
    }

    private void validatePinCountsSize(int size){
        if(size > MAX_TRY_COUNT){
            throw new IllegalArgumentException("투구 수가 너무 많습니다.");
        }
    }

    public void addPinCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        validateTotalPinCounts(totalPinCounts() + pinCount);
        pinCounts.add(new PinCount(pinCount));
    }

    public FrameResult result() {
        return new FrameResult(scoreResult(), pinCounts);
    }

    private FrameScoreResult scoreResult() {
        if (totalPinCounts() == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT - 1) {
            return FrameScoreResult.STRIKE;
        }

        if (totalPinCounts() == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT) {
            return FrameScoreResult.SPARE;
        }

        if (totalPinCounts() < MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT) {
            return FrameScoreResult.MISS;
        }

        return FrameScoreResult.NONE;
    }

    public boolean isMatch(FrameScoreResult frameScoreResult) {
        return frameScoreResult == scoreResult();
    }

    private int totalPinCounts() {
        return pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);
    }

    public int tryCount() {
        return pinCounts.size();
    }

    public boolean isDone() {
        return pinCounts.size() >= MAX_TRY_COUNT || totalPinCounts() >= MAX_TOTAL_PIN_COUNTS;
    }
}
