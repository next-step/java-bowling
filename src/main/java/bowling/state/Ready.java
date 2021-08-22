package bowling.state;

import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Ready implements State {
    private Ready() {}

    public static Ready init() {
        return new Ready();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        if (downedPins.isMaxCount()) {
            return Strike.init();
        }
        return Progress.from(downedPins);
    }

    @Override
    public List<Integer> getScore() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isClean() {
        return false;
    }
}
