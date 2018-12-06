package domain;

import exception.PinBoundOverException;
import utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Pin {

    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;

    private static Map<Integer, Pin> pinCollection = new HashMap<>();
    private int pinCount;

    protected Pin(int pinCount) {
        if(pinCount > MAX_PIN_COUNT || pinCount < MIN_PIN_COUNT){
            throw new PinBoundOverException();
        }
        this.pinCount = pinCount;
    }

    public static Pin of(int pinCount){
        if(pinCollection.containsKey(pinCount)){
            return pinCollection.get(pinCount);
        }
        Pin pin = new Pin(pinCount);
        pinCollection.put(pinCount, pin);
        return pin;
    }

    public int getPinCount() {
        return pinCount;
    }

    @Override
    public String toString() {
        if(pinCount == 0){
            return StringUtils.GUTTER;
        }

        if(pinCount == 10){
            return StringUtils.STRIKE;
        }

        return pinCount+"";
    }
}
