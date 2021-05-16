package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class FinalPins extends Pins {
    private static final int NORMAL_PIN_SIZE = 2;
    private static final int PIN_SIZE = 3;

    private List<Pin> pins;

    public FinalPins() {
        pins = new ArrayList<>();
    }

    @Override
    public void bowl(int pin) {
        if (!validBowl()) {
            throw new CustomException(ErrorCode.INVALID_BOWL);
        }
        pins.add(new Pin(pin));
    }

    private boolean validBowl() {
        if((isStrike() || isSpare()) && pins.size()==PIN_SIZE){
            return false;
        }
        return (isStrike() || isSpare()) || pins.size() != NORMAL_PIN_SIZE;
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
        if (pins.size() < NORMAL_PIN_SIZE) {
            return false;
        }
        Pin firstPin = pins.get(FIRST);
        Pin secondPin = pins.get(SECOND);
        return secondPin.didClear(firstPin);
    }
}
