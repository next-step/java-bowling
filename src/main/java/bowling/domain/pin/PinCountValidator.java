package bowling.domain.pin;

import bowling.domain.frame.NormalFrame;
import bowling.exception.FramePinCountException;

@FunctionalInterface
public interface PinCountValidator {

    PinCountValidator NORMAL = ofNormal();
    PinCountValidator FINAL = ofFinal();

    void validate(Pins pins, Pin pin);

    static PinCountValidator ofNormal() {
        return (pins, pin) -> {
            if (pins.totalPinCount() + pin.pinCount() > NormalFrame.MAX_NORMAL_PIN_COUNT) {
                throw new FramePinCountException();
            }
        };
    }

    static PinCountValidator ofFinal() {
        return (pins, pin) -> {
            if (pins.isLastGameSpare()) {
                pins.secondPin().sum(pin);
            }
        };
    }
}
