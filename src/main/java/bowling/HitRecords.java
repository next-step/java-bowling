package bowling;

import java.util.ArrayList;
import java.util.List;

public class HitRecords {
    private static final int HIT_ONCE = 1;

    private final List<HitRecord> hitRecords;
    private Score score;

    public HitRecords() {
        this.hitRecords = new ArrayList<>();
        this.score = Score.ofZero();
    }

    public boolean hitOnce() {
        return hitRecords.size() == HIT_ONCE;
    }


    public void addRecord(HitRecord hitRecord) {
        this.hitRecords.add(hitRecord);
    }

    public void addStrike() {
        this.hitRecords.add(HitRecord.of(10, BowilingTerm.STRIKE));
        score = Score.ofStrike();
    }

    public void addSpare() {
        this.hitRecords.add(HitRecord.of(10, BowilingTerm.SPARE));
        score = Score.ofSpare();
    }

    public void addGutter() {
        this.hitRecords.add(HitRecord.of(0, BowilingTerm.GUTTER));
        score = Score.ofMiss(hitSum());
    }

    public void addMiss(int count) {
        this.hitRecords.add(HitRecord.of(count, BowilingTerm.MISS));
        score = Score.ofMiss(hitSum());
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

    private int hitSum() {
        return hitRecords.stream().mapToInt(HitRecord::getHitCount).sum();
    }

    public void calculateBonus(int hitCount) {
        if (score.remainBonus()) {
            score = score.addBonusScore(hitCount);
        }
    }

    public Score getScore() {
        return score;
    }
}
