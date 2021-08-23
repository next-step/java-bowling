package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class End implements State {
    private End() {}

    public static End init() {
        return new End();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        throw new EndStateException("더 이상 진행할 수 없습니다.");
    }

    @Override
    public List<Integer> getScore() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isClean() {
        return false;
    }
}
