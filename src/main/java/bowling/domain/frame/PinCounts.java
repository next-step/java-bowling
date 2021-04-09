package bowling.domain.frame;

import bowling.domain.State.StateType;
import bowling.dto.PinCountsResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PinCounts {

    private static final int MAX_TRY_COUNT = 2;

    private static final int MAX_TOTAL_PIN_COUNTS = 10;

    private final List<PinCount> pinCounts = new ArrayList<>();

    public PinCounts(List<PinCount> pinCounts) {
        initializePinCounts(pinCounts);
    }

    public PinCounts() {
        this(new ArrayList<>());
    }

    private void initializePinCounts(List<PinCount> pinCounts) {
        try {
            pinCounts.forEach(this::add);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new IllegalArgumentException("유효하지 않는 투구 값들 입니다.");
        }

    }

    private void validateToAddPinCount(PinCount pinCount) {
        if (totalPinCounts() + pinCount.count() > MAX_TOTAL_PIN_COUNTS) {
            throw new IllegalArgumentException("투구 결과 핀수가 너무 많습니다.");
        }
    }

    public void add(PinCount pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        validateToAddPinCount(pinCount);
        pinCounts.add(pinCount);
    }

    public void add(int pinCount) {
        add(new PinCount(pinCount));
    }

    public boolean isDone() {
        return pinCounts.size() >= MAX_TRY_COUNT || totalPinCounts() >= MAX_TOTAL_PIN_COUNTS;
    }

    public int totalPinCounts() {
        return pinCounts.stream()
                .map(PinCount::count)
                .reduce(0, Integer::sum);
    }

    private StateType currentState() {

        int totalCount = totalPinCounts();

        if (totalCount == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT - 1) {
            return StateType.STRIKE;
        }

        if (totalCount == MAX_TOTAL_PIN_COUNTS && pinCounts.size() == MAX_TRY_COUNT) {
            return StateType.SPARE;
        }

        if (totalCount < MAX_TOTAL_PIN_COUNTS && totalCount > 0
                && pinCounts.size() == MAX_TRY_COUNT) {
            return StateType.MISS;
        }

        return StateType.NONE;
    }

    public boolean isMatchCurrentState(StateType stateType) {
        return currentState() == stateType;
    }

    private List<Integer> pinCountsAsInt() {
        return pinCounts.stream()
                .map(PinCount::count)
                .collect(Collectors.toList());
    }

    public PinCountsResult result() {
        return new PinCountsResult(currentState(), pinCountsAsInt());
    }

    public int hitCount() {
        return pinCounts.size();
    }
}
