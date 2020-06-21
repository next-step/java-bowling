package bowling.domain.frameResult;

import bowling.domain.FrameStatus;
import bowling.domain.FrameStatuses;

import java.util.Collections;
import java.util.Objects;

public class StrikeFrameResult implements FrameResult {
    private final int NUMBER_OF_HIT_PIN = 10;

    @Override
    public boolean isStrikeResult() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public FrameStatuses calculateCurrentStatus() {
        return new FrameStatuses(Collections.singletonList(FrameStatus.STRIKE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StrikeFrameResult that = (StrikeFrameResult) o;
        return NUMBER_OF_HIT_PIN == that.NUMBER_OF_HIT_PIN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NUMBER_OF_HIT_PIN);
    }
}
