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
        return Lists.getAsOptional(values, 0).orElse(null);
    }

    public Integer getSecond() {
        return Lists.getAsOptional(values, 1).orElse(null);
    }

    public Integer getThird() {
        return Lists.getAsOptional(values, 2).orElse(null);
    }
}
