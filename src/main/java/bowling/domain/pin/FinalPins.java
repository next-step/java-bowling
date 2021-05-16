package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class FinalPins extends Pins {
    private static final int NORMAL_PIN_SIZE = 2;

    private boolean newPins;
    private List<Pin> pins;

    public FinalPins() {
        newPins = true;
        pins = new ArrayList<>();
    }

    @Override
    public void bowl(int pin) {
        if (isEnd()) {
            throw new CustomException(ErrorCode.INVALID_BOWL);
        }
        if (newPins) {
            bowlNewPins(pin);
            return;
        }
        bowlOldPins(pin);
    }

    private void bowlNewPins(int pin) {
        pins.add(new Pin(pin));
        if (pin != MAXIMUM_PINS) {
            newPins = false;
        }
    }

    private void bowlOldPins(int pin) {
        Pin lastPin = pins.get(pins.size() - 1);
        pins.add(new Pin(lastPin, pin));
        newPins = true;
    }

    @Override
    public boolean isEnd() {
        if (pins.size() < NORMAL_PIN_SIZE) {
            return false;
        }
        if (pins.size() == NORMAL_PIN_SIZE) {
            Pin firstPin = pins.get(FIRST);
            Pin secondPin = pins.get(SECOND);
            return !(firstPin.didClear() || secondPin.didClear(firstPin));
        }
        return true;
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
        if (isStrike() || pins.size() < NORMAL_PIN_SIZE) {
            return false;
        }
        return pins.get(SECOND).didClear(pins.get(FIRST));
    }

    @Override
    public Pin pin(int index) {
        return pins.get(index);
    }

    @Override
    public int size() {
        return pins.size();
    }
}
