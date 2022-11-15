package bowling;

public class HitRecord {
    private final int hitCount;
    private final BowilingTerm bowilingTerm;

    public static HitRecord of(int count, BowilingTerm bowilingTerm) {
        return new HitRecord(count, bowilingTerm);
    }

    public HitRecord(int count, BowilingTerm bowilingTerm) {
        this.hitCount = count;
        this.bowilingTerm = bowilingTerm;
    }

    public int getHitCount() {
        return hitCount;
    }

    public BowilingTerm getBowilingTerm() {
        return bowilingTerm;
    }

    public boolean hitAll() {
        return hitCount == BowlingPin.MAX_PIN_NUMBER;
    }

    public boolean hitZero() {
        return hitCount == BowlingPin.ZERO;
    }
}
