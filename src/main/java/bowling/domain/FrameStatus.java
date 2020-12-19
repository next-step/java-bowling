package bowling.domain;

import bowling.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class FrameStatus {
    protected final List<Integer> values;

    public FrameStatus(Integer... fallingPinValues) {
        values = Arrays.stream(fallingPinValues)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public Integer getFirst() {
        return Lists.getOrNull(values, 0);
    }

    public Integer getSecond() {
        return Lists.getOrNull(values, 1);
    }

    public Integer getThird() {
        return Lists.getOrNull(values, 2);
    }
}
