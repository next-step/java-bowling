package bowling.domain;

import java.util.HashMap;
import java.util.Map;

import static bowling.utils.BowlingConstants.MAX_HIT;
import static bowling.utils.BowlingConstants.MIN_HIT;

public class Pin {

    private static final Map<Integer, Pin> reusablePin = new HashMap<>();

    private int pinNum;

    private Pin(int pinNum) {
        this.pinNum = pinNum;
    }

    public static Pin getInstance(int pinNum) {
        if(reusablePin.get(pinNum) != null) {
            return reusablePin.get(pinNum);
        }

        Pin pin = new Pin(pinNum);
        reusablePin.put(pinNum, pin);
        return reusablePin.get(pinNum);
    }

    public Pin add(Pin pin) {
        return getInstance(this.pinNum + pin.pinNum);
    }

    public boolean exceedMaxHit() {
        return this.pinNum > MAX_HIT;
    }

    public boolean belowMinHit() {
        return this.pinNum < MIN_HIT;
    }

    public boolean isMinHit() {
        return this.pinNum == MIN_HIT;
    }

    public boolean isMaxHit() {
        return this.pinNum == MAX_HIT;
    }

    @Override
    public String toString() {
        return String.valueOf(pinNum);
    }
}
