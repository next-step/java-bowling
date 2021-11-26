package bowling.domain.value;

import bowling.utils.Preconditions;

import java.util.List;

public class Pins {
    private final List<Pin> pins;

    private Pins(List<Pin> pins) {
        Preconditions.checkEmpty(pins, "pins는 필수값입니다.");

        this.pins = pins;
    }

    public static Pins from(List<Pin> pins) {
        return new Pins(pins);
    }
}
