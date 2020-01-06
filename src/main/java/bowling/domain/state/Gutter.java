package bowling.domain.state;

import static bowling.domain.FrameConstants.MAX_HIT_COUNT;
import static bowling.domain.FrameConstants.MIN_HIT_COUNT;

public class Gutter implements State {

    public static final String TEXT = "-";

    @Override
    public State play(int newHitCount) {
        if (newHitCount == MIN_HIT_COUNT) {
            return new Miss(MIN_HIT_COUNT, MIN_HIT_COUNT);
        }

        if (newHitCount == MAX_HIT_COUNT) {
            return new Spare(newHitCount);
        }

        return new Miss(0, newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Gutter();
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return Gutter.TEXT;
    }

    @Override
    public int getHitCount() {
        return MIN_HIT_COUNT;
    }
}
