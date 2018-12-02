package bowling.domain.record;

import bowling.domain.Pin;

import static bowling.utils.BowlingConstants.MAX_HIT;

public class Strike implements Record {

    private static final String STRIKE = "X";

    private static Strike strike = new Strike();

    private Strike() {
    }

    public static Strike getInstance() {
        return strike;
    }

    public static boolean isStrike(Pin pin) {
        return pin.isMaxHit();
    }

    @Override
    public Pin hitPinCount() {
        return Pin.getInstance(MAX_HIT);
    }

    @Override
    public String recordToString() {
        return STRIKE;
    }

    @Override
    public Record nextRecord(Pin nextPin) {
        return Record.ofPinCount(nextPin);
    }
}
