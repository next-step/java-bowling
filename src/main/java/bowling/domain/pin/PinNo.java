package bowling.domain.pin;

import java.util.HashMap;
import java.util.Map;

public class PinNo {

    public static final int MIN_PIN_NO = 0;
    public static final int MAX_PIN_NO = 10;
    private static final Map<Integer, PinNo> cache = new HashMap<>();

    static {
        for (int no = MIN_PIN_NO; no <= MAX_PIN_NO; no++) {
            cache.put(no, new PinNo(no));
        }
    }

    private final int no;

    private PinNo(int no) {
        this.no = no;
    }

    public static PinNo of(int no) {
        if (no < MIN_PIN_NO || no > MAX_PIN_NO) {
            throw new IllegalArgumentException("invalid frame number: " + no);
        }
        return cache.get(no);
    }

    public boolean isMaxNo() {
        return no == MAX_PIN_NO;
    }

    public boolean isMinNo() {
        return no == MIN_PIN_NO;
    }

    public boolean canPlus(int no) {
        return this.no + no <= MAX_PIN_NO;
    }

    public PinNo plus(PinNo pinNo) {
        return PinNo.of(this.no + pinNo.no);
    }

    public int getNo() {
        return no;
    }
}
