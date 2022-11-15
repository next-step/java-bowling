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
        if (isOnGoing()) {
            return false;
        }
        // TODO : 메서드 분리하기
        int count = 0;
        Frame nextFrame = getNextFrame();
        while (nextFrame != null && nextFrame.countHits() > 0 && count < getLastState().getNumberOfBonus()) {
            count += nextFrame.countHits();
            nextFrame = nextFrame.getNextFrame();
        }
        return count >= getLastState().getNumberOfBonus();
    }

    @Override
    public int getScore() {
        if (!hasScore()) {
            throw new IllegalStateException("점수를 가질 수 없는 상태입니다.");
        }
        return calculateScore();
    }

    // TODO : 인덴트 줄이기
    private int calculateScore() {
        int count = 0;
        int index = 0;
        int sum = sumOfHits();
        Frame nextFrame = getNextFrame();
        while (count < getLastState().getNumberOfBonus()) {
            sum += nextFrame.getHit(index++);
            if (index >= nextFrame.countHits()) {
                nextFrame = nextFrame.getNextFrame();
                index = 0;
            }
            count++;
        }
        return sum;
    }

}
