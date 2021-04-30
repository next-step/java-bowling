package bowling.domain;

public final class HitCount {

    private final int count;

    private HitCount(final int count) {
        this.count = count;
    }

    public static final HitCount valueOf(final int count) {
        return new HitCount(count);
    }
}
