package bowling;

import bowling.rollresult.RollResult;

import java.util.Objects;

public class FinalFrame implements Frame {
    private final Pin pin;
    private final RollResult result;

    private FinalFrame(Pin pin) {
        this(pin, null);
    }

    private FinalFrame(Pin pin, RollResult result) {
        this.pin = pin;
        this.result = result;
    }

    public static FinalFrame of() {
        return new FinalFrame(Pin.of());
    }

    public static FinalFrame of(Pin pin) {
        return new FinalFrame(pin);
    }

    public static FinalFrame of(RollResult result) {
        return new FinalFrame(null, result);
    }

    @Override
    public Frame next(int index) {
        return this;
    }

    @Override
    public Frame roll(HitNumber rollNumber) {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pin, that.pin) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, result);
    }
}
