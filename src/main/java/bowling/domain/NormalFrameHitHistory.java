package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrameHitHistory extends AbstractHitHistory {

    private static final int MAX_SIZE_OF_HIT_HISTORY = 2;

    public NormalFrameHitHistory() {
        this(new ArrayList<>());
    }

    public NormalFrameHitHistory(List<Integer> hits) {
        super(hits);
    }

    protected void validateHits(List<Integer> hits) {
        validateSizeOfHits(hits);
        hits.forEach(this::validateHitIsNegative);
        validateSumOfHits(hits);
    }

    private void validateSizeOfHits(List<Integer> hits) {
        if (hits.size() > MAX_SIZE_OF_HIT_HISTORY) {
            throw new IllegalArgumentException(String.format("투구 기록은 최대 %d회 까지 저장할 수 있습니다.", MAX_SIZE_OF_HIT_HISTORY));
        }
    }

    private void validateSumOfHits(List<Integer> hits) {
        int sum = hits.stream().reduce(0, Integer::sum);
        if (sum > BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN) {
            throw new IllegalArgumentException(String.format("투구의 합은 최대 %d 이어야 합니다.", BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN));
        }
    }

    protected void validateHit(int hit) {
        validateState();
        validateHitIsNegative(hit);
        validateHitIsUnderRemainedPins(hit);
    }

    private void validateState() {
        if (hits.size() == MAX_SIZE_OF_HIT_HISTORY) {
            throw new IllegalStateException(String.format("투구 기록은 최대 %d회 까지 저장할 수 있습니다.", MAX_SIZE_OF_HIT_HISTORY));
        }
    }

    private void validateHitIsUnderRemainedPins(int hit) {
        if (hits.isEmpty()) {
            return;
        }

        int remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN - hits.get(0);
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", remainedPins));
        }
    }

}
