package bowling;

import bowling.view.ScoreShape;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int HIT_COUNT = 2;

    private BowlingPin bowlingPin;
    private final List<HitRecord> hitRecords;

    public Frame() {
        this.bowlingPin = new BowlingPin(BowlingPin.MAX_PIN_NUMBER);
        hitRecords = new ArrayList<>();
    }

    public boolean hitBowlingPin(int count) {
        bowlingPin = bowlingPin.hitPins(new BowlingPin(count));
        hitRecords.add(new HitRecord(count));

        return clearAllFrame() || hitTwice();
    }

    public List<HitRecord> getHitRecords() {
        return hitRecords;
    }

    private boolean clearAllFrame() {
        return bowlingPin.isZero();
    }

    private boolean hitTwice() {
        return hitRecords.size() == HIT_COUNT;
    }
}
