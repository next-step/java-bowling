package bowling.domain.record;

import static bowling.utils.BowlingConstants.MIN_HIT;

public class Gutter implements Record {

    private static final String GUTTER = "-";

    private static Gutter gutter = new Gutter();

    private Gutter() {
    }

    public static Gutter getInstance() {
        return gutter;
    }

    public static boolean isGutter(int pinCount) {
        return pinCount == MIN_HIT;
    }

    @Override
    public int hitPinCount() {
        return MIN_HIT;
    }

    @Override
    public String recordToString() {
        return GUTTER;
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
