package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 3;

    public FinalBowlingGameFrame() {
        this(new ArrayList<>());
    }

    public FinalBowlingGameFrame(List<Integer> hits) {
        super(hits);
    }

    // TODO : 인덴트 줄이기 + 라인 수 줄이기
    @Override
    protected void validateHits(List<Integer> hits) {
        int remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
        for (int hit : hits) {
            if (hit > remainedPins) {
                throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS));
            }

            remainedPins -= hit;
            if (remainedPins == 0) {
                remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
            }
        }
        if (hits.size() == MAX_SIZE_OF_HITS && hits.get(0) + hits.get(1) < BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS) {
            throw new IllegalArgumentException("세번째 투구는 스트라이크이거나, 스페어인 경우에만 가능합니다.");
        }
    }

    @Override
    protected void validateState() {
        if (isMiss()) {
            throw new IllegalStateException("세번째 투구는 스트라이크이거나, 스페어인 경우에만 가능합니다.");
        }
    }

    @Override
    public int getMaxSizeOfHits() {
        return MAX_SIZE_OF_HITS;
    }

}
