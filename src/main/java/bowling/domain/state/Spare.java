package bowling.domain.state;

import static bowling.domain.FrameConstants.MIN_HIT_COUNT;

public class Spare implements State {

    public static final String TEXT = "/";

    private final int hitCount;

    public Spare(int hitCount) {
        assertHitCount(hitCount);
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Spare 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Spare(hitCount);
    }

    @Override
    public boolean isBonusPlayableState() {
        return true;
    }

    @Override
    public String getString() {
        return Spare.TEXT;
    }

    @Override
    public int getScore() {
        return hitCount;
    }

    private void assertHitCount(int hitCount) {
        if (hitCount <= MIN_HIT_COUNT) {
            throw new IllegalArgumentException("올바르지 않은 상태 입니다. : Spare");
        }
    }
}
