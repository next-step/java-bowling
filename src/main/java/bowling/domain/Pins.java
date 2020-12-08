package bowling.domain;

import static util.Preconditions.checkArgument;

public class Pins {
    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;
    
    public static final String INVALID_PIN_COUNT = "쓰러 진 볼링핀의 개수가 유효하지 않습니다.";

    private final int fallenPin;

    private Pins(final int fallenPin) {
        this.fallenPin = fallenPin;
    }

    public static Pins of(final int fallenPin) {
        checkArgument(fallenPin >= MINIMUM_COUNT && fallenPin <= MAXIMUM_COUNT, INVALID_PIN_COUNT);
        return new Pins(fallenPin);
    }
}
