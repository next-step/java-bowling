package bowling.domain.frame;

import bowling.domain.state.FrameState;

public class NormalFrame extends AbstractFrame {

    private static final int MAX_SIZE_OF_HITS = 2;

    public NormalFrame(Frame nextFrame) {
        super(nextFrame);
    }

    @Override
    public boolean isEnded() {
        return states.contains(FrameState.STRIKE) || hits.size() == MAX_SIZE_OF_HITS;
    }

    @Override
    public boolean hasScore() {
        return isEnded() && getLastState().hasScore(this);
    }

    @Override
    public int getScore() {
        if (!hasScore()) {
            throw new IllegalStateException("점수를 가질 수 없는 상태입니다.");
        }
        return getLastState().calculateScore(this);
    }

}
