package bowling.domain;

import java.util.Objects;
import java.util.Optional;

public class RollingResult {

    private final PinCount pinCount;
    private final Result result;
    private RollingResult after;

    private RollingResult(PinCount pinCount, Result result, RollingResult after) {
        this.pinCount = pinCount;
        this.result = result;
        this.after = after;
    }

    public static RollingResult createFirst(Frame frame, int pinCount) {
        return createFirst(frame, PinCount.of(pinCount));
    }

    public static RollingResult createFirst(Frame frame, PinCount pinCount) {
        return new RollingResult(pinCount, Result.from(frame, pinCount), null);
    }

    public RollingResult createNext(Frame frame, int pinCount) {
        return createNext(frame, PinCount.of(pinCount));
    }

    public RollingResult createNext(Frame frame, PinCount pinCount) {
        RollingResult after = RollingResult.createFirst(frame, pinCount);
        this.after = after;
        return after;
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

    public RollingResult getAfter() {
        return after;
    }

    public PinCount getAfterPinCount() {
        return Optional.ofNullable(after)
                .map(RollingResult::getPinCount)
                .orElseGet(() -> PinCount.of(0));
    }

    public PinCount getAfterAfterPinCount() {
        return Optional.ofNullable(after)
                .map(RollingResult::getAfterPinCount)
                .orElseGet(() -> PinCount.of(0));
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
