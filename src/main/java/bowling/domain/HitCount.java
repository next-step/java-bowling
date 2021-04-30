package bowling.domain;

public class HitCount {
    private final int count;

    private HitCount(int count) {
        this.count = count;
    }

    public static final HitCount valueOf(int count) {
        return new HitCount(count);
    }

    public int count() {
        return count;
    }
}
