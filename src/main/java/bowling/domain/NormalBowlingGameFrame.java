package bowling.domain;

public class NormalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 2;

    public NormalBowlingGameFrame(BowlingGameFrame nextFrame) {
        super(nextFrame);
    }

    @Override
    public boolean isEnded() {
        return states.contains(BowlingGameHitState.STRIKE) || hits.size() == MAX_SIZE_OF_HITS;
    }

    @Override
    public boolean hasScore() {
        BowlingGameHitState state = states.get(states.size() - 1);
        return state.hasScore(this);
    }

    @Override
    public int getScore() {
        return 0;
    }

}
