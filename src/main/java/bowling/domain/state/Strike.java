package bowling.domain.state;

import bowling.domain.FrameConstants;
import bowling.domain.score.Score;

public class Strike implements State, LastState {

    public static final String TEXT = "X";

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Strike 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Strike();
    }

    @Override
    public boolean isBonusPlayableState() {
        return true;
    }

    @Override
    public String getString() {
        return Strike.TEXT;
    }

    @Override
    public int getHitCount() {
        return FrameConstants.MAX_HIT_COUNT;
    }

    @Override
    public Score createScore() {
        return new Score(10, 2);
    }
}
