package bowling;

import bowling.rollresult.RollResult;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int MAX_INDEX = 10;

    private final Pin pin;
    private RollResult result;

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

    @Override
    public Frame next(int index) {
        if(index == MAX_INDEX) {
            return FinalFrame.of();
        }
        return of();
    }

    @Override
    public boolean roll(HitNumber rollNumber) {

        return false;
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
