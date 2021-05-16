package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class NormalPins extends Pins {
    private static final int PIN_SIZE = 2;

    private List<Pin> pins;

    public NormalPins() {
        pins = new ArrayList<>();
    }

    @Override
    public void bowl(int pin) {
        if (isEnd()) {
            throw new CustomException(ErrorCode.INVALID_BOWL);
        }
        if(pins.isEmpty()){
            pins.add(new Pin(pin));
            return;
        }
        pins.add(new Pin(pins.get(FIRST), pin));
    }

    @Override
    public boolean isEnd() {
        return isStrike() || isSpare() || pins.size() == PIN_SIZE;
    }

    @Override
    public boolean isStrike() {
        if (pins.size() == NOT_PLAYED) {
            return false;
        }
        return pins.get(FIRST).didClear();
    }

    @Override
    public boolean isSpare() {
        if (isStrike()) {
            return false;
        }
        if (pins.size() != PIN_SIZE) {
            return false;
        }
        Pin firstPin = pins.get(FIRST);
        Pin secondPin = pins.get(SECOND);
        return secondPin.didClear(firstPin);
    }
}
