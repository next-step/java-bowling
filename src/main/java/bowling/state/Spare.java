package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Spare implements State {
    private final Pin downedPins;

    private Spare(final Pin downedPins) {
        this.downedPins = downedPins;
    }

    public static Spare from(final Pin downedPins) {
        return new Spare(downedPins);
    }

    @Override
    public State nextPitch(final Pin pin) {
        throw new EndStateException("더 이상 진행할 수 없습니다.");
    }

    @Override
    public List<Integer> getScore() {
        return Collections.singletonList(downedPins.parseInt());
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
