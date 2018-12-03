package bowling.domain.record;

import bowling.domain.Pin;

import static bowling.utils.BowlingConstants.MIN_HIT;

public class Gutter implements Record {

    private static final String GUTTER = "-";

    private static Gutter gutter = new Gutter();

    private Gutter() {
    }

    public static Gutter getInstance() {
        return gutter;
    }

    static boolean isGutter(Pin pin) {
        return pin.isMinHit();
    }

    @Override
    public Pin hitPinCount() {
        return Pin.getInstance(MIN_HIT);
    }

    @Override
    public String recordToString() {
        return GUTTER;
    }

    @Override
    public Record nextRecord(Pin nextPin) {
        Pin total = nextPin.add(this.hitPinCount());

        if(Gutter.isGutter(nextPin)) {
            return Gutter.getInstance();
        }

        if(Spare.isSpare(total)) {
            return Spare.getInstance();
        }

        return Miss.getInstance(nextPin);
    }
}
