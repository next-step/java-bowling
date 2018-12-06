package bowling.domain.record;

import bowling.domain.Pin;

public interface Record {

    static Record ofPinCount(Pin pin) {
        if(Strike.isStrike(pin)) {
            return Strike.getInstance();
        }

        if(Gutter.isGutter(pin)) {
            return Gutter.getInstance();
        }

        return Miss.getInstance(pin);
    }

    Pin hitPinCount();
    String recordToString();
    Record nextRecord(Pin pin);
    int hitPinCountToInteger();
}
