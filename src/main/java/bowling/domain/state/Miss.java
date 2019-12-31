package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.domain.FrameConstants.MIN_HIT_COUNT;

public class Miss implements State, LastState {

    private final int previousHitCount;
    private final int currentHitCount;

    public Miss(int previousHitCount, int currentHitCount) {
        assertHitCount(previousHitCount, currentHitCount);
        this.previousHitCount = previousHitCount;
        this.currentHitCount = currentHitCount;
    }

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Miss 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Miss(previousHitCount, currentHitCount);
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return String.valueOf(currentHitCount);
    }

    @Override
    public int getHitCount() {
        return currentHitCount;
    }

    private void assertHitCount(int previousHitCount, int currentHitCount) {
        if (previousHitCount <= MIN_HIT_COUNT || currentHitCount <= MIN_HIT_COUNT) {
            throw new IllegalArgumentException("올바르지 않은 상태 입니다. : MISS");
        }
    }


    @Override
    public Score createScore() {
        return new Score(previousHitCount + currentHitCount, 0);
    }
}