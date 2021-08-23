package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Strike implements State {
    private Strike() {}

    public static Strike init() {
        return new Strike();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        throw new EndStateException("더 이상 진행할 수 없습니다.");
    }

    @Override
    public List<Integer> getScore() {
        return Collections.singletonList(Pin.MAX_COUNT_OF_PIN);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isClean() {
        return true;
    }
}
