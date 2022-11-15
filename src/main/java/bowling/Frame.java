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

    public void hitBowlingPin(int count) {
        bowlingPin = bowlingPin.hitPins(new BowlingPin(count));
        if (strikeCondition()) {
            hitRecords.addStrike();
            return ;
        }

        if (hitRecords.hitOnce() && bowlingPin.isZero()) {
            hitRecords.addSpare();
            return ;
        }

        if (count == BowlingPin.ZERO) {
            hitRecords.addGutter();
            return ;
        }
        hitRecords.addMiss(count);
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
}
