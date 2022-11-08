package bowling.domain;

public class NormalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 2;

    @Override
    public boolean isEnded() {
        return isStrike() || hits.size() == MAX_SIZE_OF_HITS;
    }

}
