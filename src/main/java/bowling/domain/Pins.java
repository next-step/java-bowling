package bowling.domain;

public final class Pins {

    private static final int MAXIMUM_COUNT = 10;
    private static final int MINIMUM_COUNT = 0;

    private int pins = MAXIMUM_COUNT;

    private Pins() { }

    public static final Pins init() {
        return new Pins();
    }

}
