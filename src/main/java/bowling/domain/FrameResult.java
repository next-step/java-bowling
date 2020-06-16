package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameResult {
    private final List<Integer> downPins;

    public FrameResult(List<Integer> downPins) {
        this.downPins = new ArrayList<>(downPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult that = (FrameResult) o;
        return Objects.equals(downPins, that.downPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(downPins);
    }
}
