package bowling.domain.record;

import bowling.domain.Pin;

import static bowling.utils.BowlingConstants.MAX_HIT;

public class Spare implements Record {

    private static final String SPARE = "/";

    private static Spare spare = new Spare();

    private Spare() {
    }

    public static Spare getInstance() {
       return spare;
    }

    static boolean isSpare(Pin pin) {
        return pin.isMaxHit();
    }

    @Override
    public Pin hitPinCount() {
        return Pin.getInstance(MAX_HIT);
    }

    @Override
    public String recordToString() {
        return SPARE;
    }

    @Override
    public Record nextRecord(Pin nextPin) {
        return Record.ofPinCount(nextPin);
    }

    @Override
    public int hitPinCountToInteger() {
        return MAX_HIT;
    }
}
