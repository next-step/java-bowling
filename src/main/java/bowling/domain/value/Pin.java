package bowling.domain.value;

import bowling.utils.Preconditions;

public class Pin {
    private static final Integer MINIMUM_PIN_SIZE = 0;
    private static final Integer MAXIMUM_PIN_SIZE = 10;

    private final int pin;

    public Pin(int pin) {
        Preconditions.checkMinimumSize(pin, MINIMUM_PIN_SIZE,
                                       String.format("쓰러트린 핀의 갯수는 %s 이상 이어야 합니다.", MINIMUM_PIN_SIZE));
        Preconditions.checkMaximumSize(pin, MAXIMUM_PIN_SIZE,
                                       String.format("쓰러트린 핀의 갯수는 %s 이하 이어야 합니다.", MAXIMUM_PIN_SIZE));

        this.pin = pin;
    }

    public static Pin from(int pin) {
        return new Pin(pin);
    }
}
