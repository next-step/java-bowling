package bowling.entity;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class SecondPinResult implements PinResult {
    private final Pin firstResultPin;

    public SecondPinResult(Pin firstResultPin) {
        this.firstResultPin = firstResultPin;
    }

    public Score pinResult(Pin fallenPin) {
        int framePinResult = firstResultPin.pin() + fallenPin.pin();
        if (framePinResult == MAX_PIN_COUNT) {
            return new Spare(firstResultPin, fallenPin);
        }

        return new Miss(firstResultPin, fallenPin);
    }
}
