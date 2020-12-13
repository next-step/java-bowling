package bowling.domain.state;

import bowling.domain.FallingPinCount;
import java.util.Objects;

public class Spare implements State {

    private static final String SPARE = "|/";

    private final FallingPinCount first;
    private final FallingPinCount second;

    public Spare(FallingPinCount first, FallingPinCount second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        return this;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String reportState() {
        return convertReportPattern(first) + SPARE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spare spare = (Spare) o;
        return Objects.equals(first, spare.first) && Objects.equals(second, spare.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
