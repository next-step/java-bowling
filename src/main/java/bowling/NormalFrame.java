package bowling;

import bowling.rollresult.RollResult;
import bowling.rollresult.RollResultType;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int MAX_INDEX = 10;

    private final Pin pin;
    private final RollResult result;

    private NormalFrame(Pin pin) {
        this(pin, null);
    }

    public NormalFrame(Pin pin, RollResult result) {
        this.pin = pin;
        this.result = result;
    }

    public static NormalFrame of() {
        return new NormalFrame(Pin.of());
    }

    public static NormalFrame of(Pin pin) {
        return new NormalFrame(pin);
    }

    public static NormalFrame of(RollResult result) {
        return new NormalFrame(null, result);
    }

    public static NormalFrame of(Pin pin, RollResult result) {
        return new NormalFrame(pin, result);
    }

    @Override
    public Frame next(int index) {
        if(index == MAX_INDEX) {
            return FinalFrame.of();
        }
        return of();
    }

    @Override
    public Frame roll(HitNumber rollNumber) {
        if (result == null) {
            RollResultType type = pin.firstHit(rollNumber);
            return of(pin, RollResult.of(type));
        }
        return of(pin, result.next(pin, rollNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(pin, that.pin) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, result);
    }
}
