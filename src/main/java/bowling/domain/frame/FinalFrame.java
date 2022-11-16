package bowling.domain.frame;

import bowling.domain.state.HitState;

public class FinalFrame extends AbstractFrame {

    private static final int MAX_SIZE_OF_HITS = 3;
    private static final int MIN_SIZE_OF_HITS = 2;
    private static final int SECOND_INDEX = 1;

    public FinalFrame() {
        super(null);
    }

    @Override
    public boolean isEnded() {
        if (hits.size() < MIN_SIZE_OF_HITS) {
            return false;
        }
        return HitState.MISS.equals(getSecondHitState())
                || hits.size() == MAX_SIZE_OF_HITS;
    }

    private HitState getSecondHitState() {
        return getState(SECOND_INDEX);
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

    @Override
    public String toString() {
        return "FinalFrame{" +
                "hits=" + hits +
                ", nextFrame=" + nextFrame +
                '}';
    }

}
