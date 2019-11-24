package bowling.domain.state;

import static bowling.domain.FrameConstants.MAX_HIT_COUNT;

public class Hit implements State {

    private final int hitCount;

    public Hit(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        assertNewHitCount(newHitCount);

        if (newHitCount == 0) {
            return new SecondGutter();
        }

        if (hitCount + newHitCount == MAX_HIT_COUNT) {
            return new Spare();
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

    private void assertNewHitCount(int newHitCount) {
        if (hitCount + newHitCount > MAX_HIT_COUNT) {
            throw new IllegalArgumentException();
        }
    }
}
