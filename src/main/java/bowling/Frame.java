package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private BowlingPin bowlingPin;
    private final List<HitRecord> hitRecords;

    public Frame() {
        this.bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
        hitRecords = new ArrayList<>();
    }

    public boolean hitBowlingPin(int count) {
        bowlingPin = bowlingPin.hitPins(new BowlingPin(count));
        hitRecords.add(new HitRecord(count));

        return bowlingPin.isZero() || hitRecords.size() == 2;
    }

    public List<HitRecord> getHitRecords() {
        return hitRecords;
    }
}
