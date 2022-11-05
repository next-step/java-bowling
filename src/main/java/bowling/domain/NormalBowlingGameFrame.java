package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 2;

    public NormalBowlingGameFrame() {
        this(new ArrayList<>());
    }

    public NormalBowlingGameFrame(List<Integer> hits) {
        super(hits);
    }

    protected void validateHits(List<Integer> hits) {
        int sum = hits.stream()
                .reduce(0, Integer::sum);
        if (sum > BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS) {
            throw new IllegalArgumentException(String.format("투구의 합은 최대 %d 이어야 합니다.", BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS));
        }
    }

    @Override
    public int getMaxSizeOfHits() {
        return MAX_SIZE_OF_HITS;
    }

    @Override
    public boolean isEnded() {
        return isStrike() || hits.size() == MAX_SIZE_OF_HITS;
    }

}
