package bowling.state;

import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class Progress implements State {
    private Pin downedPins;

    private Progress(final Pin downedPins) {
        this.downedPins = downedPins;
    }

    public static Progress from(final Pin pin) {
        return new Progress(pin);
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        final Pin totalPin = this.downedPins.willReturnSum(downedPins);
        if (totalPin.isMaxCount()) {
            return Spare.from(downedPins);
        }
        return Miss.from(this.downedPins, downedPins);
    }

    @Override
    public List<Integer> getScore() {
        return Collections.singletonList(downedPins.parseInt());
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
