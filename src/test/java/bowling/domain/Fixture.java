package bowling.domain;

import bowling.domain.pin.DownedPins;

public class Fixture {

    public static final DownedPins DOWNED_PINS_10 = DownedPins.from(10);
    public static final DownedPins DOWNED_PINS_5 = DownedPins.from(5);
    public static final DownedPins DOWNED_PINS_2 = DownedPins.from(2);

    private Fixture() {}
}
