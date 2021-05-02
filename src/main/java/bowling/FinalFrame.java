package bowling;

import java.util.Objects;

public class FinalFrame implements Frame {
    private final Pin pin;
    private RollResult result;

    private FinalFrame(Pin pin) {
        this.pin = pin;
    }

    public static FinalFrame of() {
        return new FinalFrame(Pin.of());
    }

    public static FinalFrame of(Pin pin) {
        return new FinalFrame(pin);
    }

    @Override
    public Frame next(int index) {
        return this;
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
