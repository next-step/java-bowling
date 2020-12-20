package bowling.domain;

import bowling.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static bowling.domain.BallThrow.MAX_PINS;
import static java.util.stream.Collectors.toList;

public class Score {
    private final List<Integer> values;

    public Score(Integer... fallingPins) {
        values = Arrays.stream(fallingPins)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public Integer toInt() {
        if (first() == null || second() == null) {
            return null;
        }

        if (first() == MAX_PINS) {
            return sumAll();
        }

        if (first() + second() == MAX_PINS) {
            return sumAll();
        }
        return first() + second();
    }

    private Integer first() {
        return Lists.getOrNull(values, 0);
    }

    private Integer second() {
        return Lists.getOrNull(values, 1);
    }

    private Integer third() {
        return Lists.getOrNull(values, 2);
    }

    private Integer sumAll() {
        if (third() == null) {
            return null;
        }
        return values.stream().reduce(0, Integer::sum);
    }
}
