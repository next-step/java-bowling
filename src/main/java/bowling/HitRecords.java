package bowling;

import java.util.ArrayList;
import java.util.List;

public class HitRecords {
    private static final int HIT_ONCE = 1;

    private List<HitRecord> hitRecords;

    public HitRecords() {
        this.hitRecords = new ArrayList<>();
    }

    public boolean hitOnce() {
        return hitRecords.size() == HIT_ONCE;
    }


    public void addRecord(HitRecord hitRecord) {
        this.hitRecords.add(hitRecord);
    }

    public void addStrike() {
        this.hitRecords.add(HitRecord.of(10, BowilingTerm.STRIKE));
    }

    public void addSpare() {
        this.hitRecords.add(HitRecord.of(10, BowilingTerm.SPARE));
    }

    public void addGutter() {
        this.hitRecords.add(HitRecord.of(0, BowilingTerm.GUTTER));
    }

    public void addMiss(int count) {
        this.hitRecords.add(HitRecord.of(count, BowilingTerm.MISS));
    }

    public boolean isRecordAllStrike() {
        return hitRecords.stream()
                .allMatch(HitRecord::hitAll);
    }

    public boolean hitTimes(int hitTwice) {
        return hitRecords.size() == hitTwice;
    }

    public List<HitRecord> getHitRecords() {
        return hitRecords;
    }
}
