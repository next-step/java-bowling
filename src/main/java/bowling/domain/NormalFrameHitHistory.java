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
        int sum = hits.stream()
                .reduce(0, Integer::sum);
        if (sum > BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN) {
            throw new IllegalArgumentException(String.format("투구의 합은 최대 %d 이어야 합니다.", BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN));
        }
    }

    protected void validateState() {
        if (isStrike()) {
            throw new IllegalStateException("두번째 투구는 스트라이크가 아닌 경우에만 가능합니다.");
        }
    }

    @Override
    public int getMaxSizeOfHitHistory() {
        return MAX_SIZE_OF_HIT_HISTORY;
    }

}
