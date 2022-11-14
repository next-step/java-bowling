package bowling.domain;

public class FinalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 3;

    public FinalBowlingGameFrame() {
        super(null);
    }

    @Override
    public boolean isEnded() {
        return states.contains(BowlingGameHitState.MISS) || hits.size() == MAX_SIZE_OF_HITS;
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
