package bowling.domain.state;

import bowling.domain.FallingPinCount;
import java.util.Objects;

public class Open implements State {

    protected static final String SPLITTER = "|";
    protected final FallingPinCount first;
    protected final FallingPinCount second;

    protected Open(FallingPinCount first, FallingPinCount second) {
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
        return convertReportPattern(first) + SPLITTER + convertReportPattern(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Open open = (Open) o;
        return Objects.equals(first, open.first) && Objects.equals(second, open.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
