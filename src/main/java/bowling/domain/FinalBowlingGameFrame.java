package bowling.domain;

public class FinalBowlingGameFrame extends AbstractBowlingGameFrame {

    private static final int MAX_SIZE_OF_HITS = 3;

    @Override
    public boolean isEnded() {
        return isMiss() || hits.size() == MAX_SIZE_OF_HITS;
    }

}
