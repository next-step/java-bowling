package bowling.domain.record;

import static bowling.utils.BowlingConstants.MAX_HIT;

public class Strike implements Record {

    private static final String STRIKE = "X";

    private static Strike strike = new Strike();

    private Strike() {
    }

    public static Strike getInstance() {
        return strike;
    }

    public static boolean isStrike(int pinCount) {
        return pinCount == MAX_HIT;
    }

    @Override
    public int hitPinCount() {
        return MAX_HIT;
    }

    @Override
    public String recordToString() {
        return STRIKE;
    }

    @Override
    public Record nextRecord(int nextPinCount) {
        return Record.ofPinCount(nextPinCount);
    }
}
