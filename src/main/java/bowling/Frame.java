package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int HIT_TWICE = 2;
    private static final int HIT_TRIPLE = 3;

    private BowlingPin bowlingPin;
    private final List<HitRecord> hitRecords;

    public Frame() {
        this.bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
        hitRecords = new ArrayList<>();
    }

    public boolean finishLastFrame() {
        return hitThreeTimes() || hitDouble() || failedBounsFrame();
    }

    private boolean failedBounsFrame() {
        return hitTwice() && !clearAllFrame();
    }

    public void hitBowlingPin(int count) {
        bowlingPin = bowlingPin.hitPins(new BowlingPin(count));
        hitRecords.add(new HitRecord(count));
    }

    public boolean finishFrame() {
        return clearAllFrame() || hitTwice();
    }

    public boolean hitDouble() {
        if (hitRecords.size() != HIT_TWICE) {
            return false;
        }
        for (HitRecord hitRecord : hitRecords) {
            if (!hitRecord.hitAll()) {
                return false;
            }
        }
        return true;
    }

    public void chargeBowlingPin() {
        bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
    }

    public List<HitRecord> getHitRecords() {
        return hitRecords;
    }

    public boolean clearAllFrame() {
        return bowlingPin.isZero();
    }

    public boolean hitThreeTimes() {
        return hitRecords.size() == HIT_TRIPLE;
    }

    public boolean hitTwice() {
        return hitRecords.size() == HIT_TWICE;
    }
}
