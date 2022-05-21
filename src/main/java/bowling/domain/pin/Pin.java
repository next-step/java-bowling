package bowling.domain.pin;

import java.util.HashMap;
import java.util.Map;

public class Pin {

    private static final int MIN_PIN_NO = 0;
    private static final int MAX_PIN_NO = 10;
    private static final Map<Integer, Pin> cache = new HashMap<>();

    static {
        for (int no = MIN_PIN_NO; no <= MAX_PIN_NO; no++) {
            cache.put(no, new Pin(no));
        }
    }

    private final int no;

    private Pin(int no) {
        this.no = no;
    }

    public static Pin of(int no) {
        if (no < MIN_PIN_NO || no > MAX_PIN_NO) {
            throw new IllegalArgumentException("invalid pin number: " + no);
        }
        return cache.get(no);
    }

    public boolean isSpareWith(Pin anotherPin) {
        return this.no + anotherPin.no == MAX_PIN_NO;
    }

    public boolean isMissWith(Pin anotherPin) {
        return this.no + anotherPin.no < MAX_PIN_NO;
    }

    public boolean isStrike() {
        return no == MAX_PIN_NO;
    }

    public int sum(Pin pin) {
        return this.no + pin.no;
    }

    public int sum(int score) {
        return this.no + score;
    }

    public int no() {
        return no;
    }
}
