package bowling;

import java.util.List;

public class Frame {
    protected static final int HIT_TWICE = 2;
    protected static final int HIT_TRIPLE = 3;

    protected BowlingPin bowlingPin;
    protected final HitRecords hitRecords;

    public Frame() {
        this.bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
        this.hitRecords = new HitRecords();
    }

    public BowilingTerm hitBowlingPin(int count) {
        bowlingPin = bowlingPin.hitPins(new BowlingPin(count));
        if (hitRecords.hitOnce() && bowlingPin.isZero()) {
            hitRecords.addSpare();
            return BowilingTerm.SPARE;
        }

        if (strikeCondition()) {
            hitRecords.addStrike();
            return BowilingTerm.STRIKE;
        }

        if (count == BowlingPin.ZERO) {
            hitRecords.addGutter();
            return BowilingTerm.GUTTER;
        }
        hitRecords.addMiss(count);
        return BowilingTerm.MISS;
    }

    protected boolean strikeCondition() {
        return bowlingPin.isZero();
    }

    protected boolean finishFrame() {
        return clearAllFrame() || hitRecords.hitTimes(HIT_TWICE);
    }

    public void chargeBowlingPin() {
        bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
    }

    public List<HitRecord> getHitRecords() {
        return hitRecords.getHitRecords();
    }

    public boolean clearAllFrame() {
        return bowlingPin.isZero();
    }

    public void calculateBonus(int hitCount) {
        hitRecords.calculateBonus(hitCount);
    }

    public HitRecords hitRecords() {
        return hitRecords;
    }
}
