package bowling.domain.state;

import static bowling.domain.FrameConstants.MAX_HIT_COUNT;
import static bowling.domain.FrameConstants.MIN_HIT_COUNT;

public class Ready implements State {

    @Override
    public State play(int newHitCount) {
        if (newHitCount == MAX_HIT_COUNT) {
            return new Strike();
        }

        if (newHitCount == 0) {
            return new FirstGutter();
        }

        return new Hit(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Ready();
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return "";
    }

    @Override
    public int getHitCount() {
        return MIN_HIT_COUNT;
    }
}
