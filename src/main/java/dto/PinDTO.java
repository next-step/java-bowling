package dto;

public class PinDTO implements DTO {

    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private final int pin;

    private PinDTO(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public static void validate(int pin) throws IllegalArgumentException {
        if (pin < MIN_PINS || pin > MAX_PINS) {
            throw new IllegalArgumentException("입력된 핀 갯수가 범위를 벗어났습니다.");
        }
    }

    public static PinDTO of(int pin) {
        validate(pin);
        return new PinDTO(pin);
    }
}
