package bowling.domain.record;

import bowling.domain.Pin;

import java.util.HashMap;
import java.util.Map;

public class Miss implements Record {

    private static final Map<Pin, Miss> reusableMiss = new HashMap<>();

    private Pin pin;

    private Miss(Pin pin) {
        this.pin = pin;
    }

    public static Miss getInstance(Pin pin) {
        if(reusableMiss.get(pin) != null) {
            return reusableMiss.get(pin);
        }

        Miss miss = new Miss(pin);
        reusableMiss.put(pin, miss);
        return reusableMiss.get(pin);
    }

    @Override
    public Pin hitPinCount() {
        return pin;
    }

    @Override
    public String recordToString() {
        return String.valueOf(this.pin);
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
