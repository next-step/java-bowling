package bowling.domain.frame;

import bowling.domain.state.FrameState;

public class FinalFrame extends AbstractFrame {

    private static final int MAX_SIZE_OF_HITS = 3;

    public FinalFrame() {
        super(null);
    }

    @Override
    public boolean isEnded() {
        return states.contains(FrameState.MISS) || hits.size() == MAX_SIZE_OF_HITS;
    }

    @Override
    public boolean hasScore() {
        return isEnded();
    }

    @Override
    public int getScore() {
        if (!hasScore()) {
            throw new IllegalStateException("점수를 가질 수 없는 상태입니다.");
        }
        return sumOfHits();
    }

}
