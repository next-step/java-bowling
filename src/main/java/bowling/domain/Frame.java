package bowling.domain;

import bowling.dto.FrameResult;
import bowling.dto.FrameScoreResult;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int MAX_TRY_COUNT = 2;

    private static final int MAX_TOTAL_PIN_COUNTS = 10;

    private final List<PinCount> pinCounts = new ArrayList<>();

    public void addPintCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        if (totalPinCounts() + pinCount > MAX_TOTAL_PIN_COUNTS) {
            throw new IllegalStateException("추가 할 수 없는 투구 수 입니다.");
        }
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
