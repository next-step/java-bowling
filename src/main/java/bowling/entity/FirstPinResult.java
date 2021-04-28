package bowling.entity;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class FirstPinResult implements PinResult {
    public Score pinResult(Pin fallenPin) {
        if (fallenPin.pin() == MAX_PIN_COUNT) {
            return new Strike(fallenPin);
        }

        return new NormalScore(fallenPin);
    }
}
