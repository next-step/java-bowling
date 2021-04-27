package bowling.domain;

public final class Pins {

    private static final int MAXIMUM_COUNT = 10;
    private static final int MINIMUM_COUNT = 0;

    private int remain;

    private Pins() {
        this(MAXIMUM_COUNT);
    }

    private Pins(int remain) {
        this.remain = remain;
    }

    public static final Pins init() {
        return new Pins();
    }

    public Pins hit(int hitCount) {
        return new Pins(Math.subtractExact(remain, hitCount));
    }
}
