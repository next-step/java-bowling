package bowling.domain;

import java.util.Objects;

public class Bowling {

    private final PinCount pinCount;
    private final Result result;

    private Bowling(PinCount pinCount, Result result) {
        this.pinCount = pinCount;
        this.result = result;
    }

    public static Bowling from(int count, Result result) {
        return new Bowling(PinCount.of(count), result);
    }

    public static Bowling from(Frame frame, int count) {
        return new Bowling(PinCount.of(count), Result.from(frame, count));
    }

    public static Bowling from(Frame frame, PinCount pinCount) {
        return new Bowling(pinCount, Result.from(frame, pinCount));
    }

    public int getCount() {
        return pinCount.getValue();
    }

    public PinCount getPinCount() {
        return pinCount;
    }

    public boolean isStrike() {
        return result == Result.STRIKE;
    }

    public Result getResult() {
        return result;
    }

    //==============================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowling bowling = (Bowling) o;
        return Objects.equals(pinCount, bowling.pinCount) && result == bowling.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount, result);
    }

    @Override
    public String toString() {
        return "Bowling{" +
                "pinCount=" + pinCount +
                ", result=" + result +
                '}';
    }
}
