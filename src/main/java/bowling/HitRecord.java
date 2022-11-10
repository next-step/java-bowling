package bowling;

public class HitRecord {
    private final int hitCount;

    public HitRecord(int count) {
        this.hitCount = count;
    }

    public int getHitCount() {
        return hitCount;
    }

    public boolean hitAll() {
        return hitCount == BowlingPin.MAX_PIN_NUMBER;
    }

    public boolean hitZero() {
        return hitCount == BowlingPin.ZERO;
    }
}
