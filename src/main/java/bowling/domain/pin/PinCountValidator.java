package bowling.domain.pin;

@FunctionalInterface
public interface PinCountValidator {

    void validate(Pins pins, Pin pin);
}
