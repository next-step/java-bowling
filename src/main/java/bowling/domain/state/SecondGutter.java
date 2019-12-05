package bowling.domain.state;

import bowling.domain.FrameConstants;

public class SecondGutter implements State {

    public static final String TEXT = "-";

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Second Gutter 에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new SecondGutter();
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return SecondGutter.TEXT;
    }

    @Override
    public int getHitCount() {
        return FrameConstants.MIN_HIT_COUNT;
    }
}
