package bowling.domain.state;

import static bowling.domain.FrameConstants.MAX_HIT_COUNT;
import static bowling.domain.FrameConstants.MIN_HIT_COUNT;

public class Hit implements State {

    private final int hitCount;

    public Hit(int hitCount) {
        assertHitCount(hitCount);
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        assertNewHitCount(newHitCount);

        if (newHitCount == 0) {
            return new SecondGutter();
        }

        if (hitCount + newHitCount == MAX_HIT_COUNT) {
            return new Spare(newHitCount);
        }

        return new Miss(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Hit(hitCount);
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return String.valueOf(hitCount);
    }

    @Override
    public int getScore() {
        return hitCount;
    }

    private void assertHitCount(int hitCount) {
        if (hitCount <= MIN_HIT_COUNT) {
            throw new IllegalArgumentException("올바르지 않은 상태 입니다. : HIT");
        }
    }

    private void assertNewHitCount(int newHitCount) {
        if (hitCount + newHitCount > MAX_HIT_COUNT) {
            throw new IllegalArgumentException("한 세트에서 10개 이상의 핀을 넘어트릴 수 없습니다.");
        }
    }
}
