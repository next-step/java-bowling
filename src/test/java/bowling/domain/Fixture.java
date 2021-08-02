package bowling.domain;

import bowling.domain.pin.DownedPins;

public class Fixture {

    public static final DownedPins DOWNED_PINS_10 = DownedPins.from(10);
    public static final DownedPins DOWNED_PINS_7 = DownedPins.from(7);
    public static final DownedPins DOWNED_PINS_5 = DownedPins.from(5);
    public static final DownedPins DOWNED_PINS_3 = DownedPins.from(3);
    public static final DownedPins DOWNED_PINS_2 = DownedPins.from(2);
    public static final DownedPins DOWNED_PINS_0 = DownedPins.from(0);

    private Fixture() {}
}
