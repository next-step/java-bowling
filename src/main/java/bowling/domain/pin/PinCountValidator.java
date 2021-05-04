package bowling.domain.pin;

import bowling.domain.frame.NormalFrame;
import bowling.exception.FramePinCountException;

@FunctionalInterface
public interface PinCountValidator {

    PinCountValidator NORMAL = ofNormal();
    PinCountValidator FINAL = ofFinal();

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

    void validate(BallThrows ballThrows, Pin pin);
}
