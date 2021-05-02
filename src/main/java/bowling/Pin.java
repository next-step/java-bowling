package bowling;

import bowling.rollresult.OneRollResultType;
import bowling.rollresult.RollResultType;

import java.util.Objects;

public class Pin {
    private static final String INVALID_PIN_TRY = "핀을 쓰러뜨릴 수 있는 횟수는 0~2회입니다.";
    private static final String INVALID_PIN_NUM = "핀의 수는 0~10개입니다.";
    private static final int PIN_TRY_LOWER_BOUND = 0;
    private static final int PIN_TRY_UPPER_BOUND = 2;
    private static final int PIN_NUM_LOWER_BOUND = 0;
    private static final int PIN_NUM_UPPER_BOUND = 10;

    private int tryNum = 0;
    private int pinNum = 10;

    private Pin(int tryNum, int pinNum) {
        this.tryNum = tryNum;
        this.pinNum = pinNum;
    }

    public Pin() {

    }

    public static Pin of() {
        return new Pin();
    }

    public static Pin of(int tryNum, int pinNum) {
        valid(tryNum, pinNum);
        return new Pin(tryNum, pinNum);
    }

    private static void valid(int tryNum, int pinNum) {
        if(tryNum < PIN_TRY_LOWER_BOUND || tryNum > PIN_TRY_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_PIN_TRY);
        }
        if(pinNum < PIN_NUM_LOWER_BOUND || pinNum > PIN_NUM_UPPER_BOUND) {
            throw new IllegalArgumentException(INVALID_PIN_NUM);
        }
    }

    private void fallen(HitNumber rollNumber) {
        tryNum++;
        pinNum = rollNumber.hit(pinNum);
    }

    public RollResultType firstHit(HitNumber rollNumber) {
        fallen(rollNumber);
        return OneRollResultType.of(PIN_NUM_UPPER_BOUND - pinNum);
    }

    public RollResultType secondHit(RollResultType type, HitNumber rollNumber) {
        valid(tryNum, pinNum);
        fallen(rollNumber);
        return type.next(PIN_NUM_UPPER_BOUND - pinNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return tryNum == pin.tryNum && pinNum == pin.pinNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tryNum, pinNum);
    }


}
