package bowling.domain.frame;

import bowling.domain.state.HitState;

public class NormalFrame extends AbstractFrame {

    private static final int MAX_SIZE_OF_HITS = 2;

    public NormalFrame(Frame nextFrame) {
        super(nextFrame);
    }

    @Override
    public boolean isEnded() {
        if (hits.isEmpty()) {
            return false;
        }
        return HitState.STRIKE.equals(getLastState())
                || hits.size() == MAX_SIZE_OF_HITS;
    }

    @Override
    public boolean hasScore() {
        if (isOnGoing()) {
            return false;
        }

        return hasEnoughHits();
    }

    private boolean hasEnoughHits() {
        int count = 0;
        Frame nextFrame = getNextFrame();
        while (hasMoreHits(count, nextFrame)) {
            count += nextFrame.countHits();
            nextFrame = nextFrame.getNextFrame();
        }
        return count >= getLastState().getNumberOfBonus();
    }

    private boolean hasMoreHits(int count, Frame nextFrame) {
        return nextFrame != null && nextFrame.countHits() > 0 && count < getLastState().getNumberOfBonus();
    }

    @Override
    public int getScore() {
        if (!hasScore()) {
            throw new IllegalStateException("점수를 가질 수 없는 상태입니다.");
        }
        return calculateScore();
    }

    private int calculateScore() {
        int sum = sumOfHits();
        for (int i = 0; i < getLastState().getNumberOfBonus(); i++) {
            sum += getNextHit(getNextFrame(), i);
        }
        return sum;
    }

    private int getNextHit(Frame frame, int index) {
        if (index >= frame.countHits()) {
            return getNextHit(frame.getNextFrame(), index - frame.countHits());
        }

        return frame.getHitValue(index);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "hits=" + hits +
                ", nextFrame=" + nextFrame +
                '}';
    }

}
