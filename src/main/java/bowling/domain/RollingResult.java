package bowling.domain;

import java.util.Objects;

public class RollingResult {

    private final PinCount pinCount;
    private final Result result;

    private RollingResult(PinCount pinCount, Result result) {
        this.pinCount = pinCount;
        this.result = result;
    }

    public static RollingResult from(Frame frame, PinCount pinCount) {
        return new RollingResult(pinCount, Result.from(frame, pinCount));
    }

    public int getCount() {
        return pinCount.getValue();
    }

    public PinCount getPinCount() {
        return pinCount;
    }

    public Result getResult() {
        return result;
    }

    //==============================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollingResult rollingResult = (RollingResult) o;
        return Objects.equals(pinCount, rollingResult.pinCount) && result == rollingResult.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount, result);
    }

    @Override
    public String toString() {
        return "RollingResult{" +
                "pinCount=" + pinCount +
                ", result=" + result +
                '}';
    }
}
