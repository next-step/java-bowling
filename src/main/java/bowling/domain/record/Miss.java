package bowling.domain.record;

import java.util.HashMap;
import java.util.Map;

public class Miss implements Record{

    private static final Map<Integer, Miss> reusableMiss = new HashMap<>();

    private int pinCount;

    private Miss(int pinCount) {
        this.pinCount = pinCount;
    }

    public static Miss getInstance(int pinCount) {
        if(reusableMiss.get(pinCount) != null) {
            return reusableMiss.get(pinCount);
        }

        Miss miss = new Miss(pinCount);
        reusableMiss.put(pinCount, miss);
        return reusableMiss.get(pinCount);
    }

    @Override
    public int hitPinCount() {
        return this.pinCount;
    }

    @Override
    public String recordToString() {
        return String.valueOf(this.pinCount);
    }

    @Override
    public Record nextRecord(int nextPinCount) {
        int totalHitCount = this.hitPinCount() + nextPinCount;

        if(Gutter.isGutter(nextPinCount)) {
            return Gutter.getInstance();
        }

        if(Spare.isSpare(totalHitCount)) {
            return Spare.getInstance();
        }

        return Miss.getInstance(nextPinCount);
    }
}
