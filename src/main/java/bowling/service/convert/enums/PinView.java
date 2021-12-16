package bowling.service.convert.enums;

import bowling.domain.frame.Pin;

import java.util.Arrays;

public enum PinView {

    GUTTER(Pin.from(0), "-"),
    ONE(Pin.from(1), "1"),
    TWO(Pin.from(2), "2"),
    THREE(Pin.from(3), "3"),
    FOUR(Pin.from(4), "4"),
    FIVE(Pin.from(5), "5"),
    SIX(Pin.from(6), "6"),
    SEVEN(Pin.from(7), "7"),
    EIGHT(Pin.from(8), "8"),
    NINE(Pin.from(9), "9"),
    STRIKE(Pin.from(10), "X");


    private final Pin pin;
    private final String desc;

    PinView(Pin pin, String desc) {
        this.pin = pin;
        this.desc = desc;
    }

    public static PinView valueOf(Pin value) {
        return Arrays.stream(values())
                .filter(view -> view.pin.equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getDesc() {
        return desc;
    }
}
