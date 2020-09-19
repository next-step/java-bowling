package dto;

import bowling.Pin;

public class PinDTO implements DTO {

    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private final int pin;

    private PinDTO(int pin) {
        this.pin = pin;
    }

    public static PinDTO of(int pin) {
        validate(pin);
        return new PinDTO(pin);
    }

    public static PinDTO from(Pin pin) {
        return new PinDTO(pin.getPin());
    }

    public static void validate(int pin) throws IllegalArgumentException {
        if (pin < MIN_PINS || pin > MAX_PINS) {
            throw new IllegalArgumentException("입력된 핀 갯수가 범위를 초과했습니다.");
        }
    }

    public int getPin() {
        return pin;
    }
}
